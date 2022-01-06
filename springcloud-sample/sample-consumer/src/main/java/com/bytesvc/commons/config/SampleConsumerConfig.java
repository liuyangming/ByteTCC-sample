package com.bytesvc.commons.config;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.config.LoadBalancerAutoConfiguration;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.SelectedInstanceCallback;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.bytesvc.commons.config.SampleConsumerConfig.CustomLoadBalancerConfiguration;

import reactor.core.publisher.Mono;

//@Configuration
@LoadBalancerClients(defaultConfiguration = { CustomLoadBalancerConfiguration.class })
@AutoConfigureAfter(LoadBalancerAutoConfiguration.class)
@AutoConfigureBefore({ org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration.class })
//@LoadBalancerClient(value = "default.compensable", configuration = CustomLoadBalancerConfiguration.class)
//@FeignClient(configuration= {CustomLoadBalancerConfiguration.class})
public class SampleConsumerConfig {
	@Bean
	ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
			LoadBalancerClientFactory loadBalancerClientFactory) {
//		String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
//		return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
		System.out.println("......");
		return new ReactorLoadBalancer<ServiceInstance>() {
			@SuppressWarnings("rawtypes")
			public Mono<Response<ServiceInstance>> choose(Request request) {
				final ObjectProvider<ServiceInstanceListSupplier> provider = //
						loadBalancerClientFactory.getProvider("default.bytetcc", ServiceInstanceListSupplier.class);
				System.out.printf("%s, %s%n", provider, request);
				ServiceInstanceListSupplier supplier = provider.getIfAvailable(NoopServiceInstanceListSupplier::new);
				return supplier.get(request).next()
						.map(serviceInstances -> processInstanceResponse(supplier, serviceInstances));
			}

			private Response<ServiceInstance> processInstanceResponse(ServiceInstanceListSupplier supplier,
					List<ServiceInstance> serviceInstances) {
				Response<ServiceInstance> serviceInstanceResponse = getInstanceResponse(serviceInstances);
				if (supplier instanceof SelectedInstanceCallback && serviceInstanceResponse.hasServer()) {
					((SelectedInstanceCallback) supplier).selectedServiceInstance(serviceInstanceResponse.getServer());
				}
				return serviceInstanceResponse;
			}

			private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances) {
				if (instances.isEmpty()) {
					return new EmptyResponse();
				}
				int index = ThreadLocalRandom.current().nextInt(instances.size());
				ServiceInstance instance = instances.get(index);
				return new DefaultResponse(instance);
			}
		};
	}

	public static class CustomLoadBalancerConfiguration {
		@Bean
		ReactorLoadBalancer<ServiceInstance> providerRandomLoadBalancer(LoadBalancerClientFactory loadBalancerClientFactory) {
			return new RandomLoadBalancer(
					loadBalancerClientFactory.getLazyProvider("default.compensable", ServiceInstanceListSupplier.class),
					"default.compensable");
		}
	}
}
