package com.retail.product.config

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
@EnableDynamoDBRepositories
class DynamoConfig {

    @Value("\${amazon.dynamodb.endpoint}")
    lateinit var endpoint: String

    @Value("\${amazon.aws.accesskey}")
    lateinit var amazonAwsAccessKey: String

    @Value("\${amazon.aws.secretkey}")
    lateinit var amazonAwsSecretKey: String

    @Value("\${amazon.aws.region}")
    lateinit var region: String

    @Bean
    @Primary
    fun dynamoDbMapperConfig(): DynamoDBMapperConfig {
        return DynamoDBMapperConfig.DEFAULT
    }

    @Bean
    @Primary
    fun dynamoDbMapper (amazonDynamoDB: AmazonDynamoDB, config: DynamoDBMapperConfig): DynamoDBMapper {
        return DynamoDBMapper(amazonDynamoDB, config)
    }

    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB {
        return if (endpoint.isNotEmpty()){
             AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(amazonAWSCredentialsProvider())
                .withEndpointConfiguration(
                    AwsClientBuilder
                        .EndpointConfiguration(endpoint, region)
                ).build()
        } else {
            AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(amazonAWSCredentialsProvider())
                .withRegion(region).build()
        }
    }

    @Bean
    fun amazonAwsCredentials(): AWSCredentials {
        return BasicAWSCredentials(amazonAwsAccessKey, amazonAwsSecretKey)
    }

    fun amazonAWSCredentialsProvider(): AWSCredentialsProvider =
        AWSStaticCredentialsProvider(amazonAwsCredentials())
}
