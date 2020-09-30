package org.testcontainers.utility;

import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO: Javadocs
 */
@Slf4j
public class ConfigurationFileImageNameSubstitutor extends ImageNameSubstitutor {

    private final TestcontainersConfiguration configuration;

    public ConfigurationFileImageNameSubstitutor() {
        this(TestcontainersConfiguration.getInstance());
    }

    @VisibleForTesting
    ConfigurationFileImageNameSubstitutor(TestcontainersConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public DockerImageName apply(final DockerImageName original) {
        final DockerImageName result = configuration
            .getConfiguredSubstituteImage(original)
            .asCompatibleSubstituteFor(original);

        if (!result.equals(original)) {
            log.warn("Image name {} was substituted by configuration to {}. This approach is deprecated and will be removed in the future",
                original,
                result
            );
        }

        return result;
    }

    @Override
    protected int getPriority() {
        return -2;
    }

    @Override
    protected String getDescription() {
        return getClass().getSimpleName();
    }
}
