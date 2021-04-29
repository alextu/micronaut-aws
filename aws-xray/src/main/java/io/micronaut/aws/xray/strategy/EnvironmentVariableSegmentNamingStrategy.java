/*
 * Copyright 2017-2021 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.aws.xray.strategy;

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.order.Ordered;
import io.micronaut.http.HttpRequest;

import javax.inject.Singleton;

/**
 * @author Sergio del Amo
 * @since 2.7.0
 */
@Requires(condition = EnvironmentVariableSegmentNamingStrategyCondition.class)
@Singleton
public class EnvironmentVariableSegmentNamingStrategy implements SegmentNamingStrategy {
    /**
     * Environment variable key used to override the default segment name used by implementors of {@code SegmentNamingStrategy}.
     * Takes precedence over any system property, web.xml configuration value, or constructor value used for a fixed segment name.
     */
    public static final String ENVIRONMENT_VARIABLE_AWS_XRAY_TRACING_NAME = "AWS_XRAY_TRACING_NAME";

    @Override
    public int getOrder() {
        return ORDER;
    }

    public static final int ORDER = Ordered.HIGHEST_PRECEDENCE + 100;

    @Override
    @NonNull
    public String nameForRequest(@NonNull HttpRequest<?> request) {
        return System.getenv(ENVIRONMENT_VARIABLE_AWS_XRAY_TRACING_NAME);
    }
}
