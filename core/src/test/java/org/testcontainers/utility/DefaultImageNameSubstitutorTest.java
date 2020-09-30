package org.testcontainers.utility;

import org.junit.Before;
import org.junit.Test;
import org.testcontainers.utility.ImageNameSubstitutor.LogWrappedImageNameSubstitutor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultImageNameSubstitutorTest {

    public static final DockerImageName ORIGINAL_IMAGE = DockerImageName.parse("foo");
    public static final DockerImageName SUBSTITUTE_IMAGE = DockerImageName.parse("bar");
    private DefaultImageNameSubstitutor underTest;
    private TestcontainersConfiguration mockConfiguration;

    @Before
    public void setUp() {
        mockConfiguration = mock(TestcontainersConfiguration.class);
        underTest = new DefaultImageNameSubstitutor(mockConfiguration);
    }

    @Test
    public void testConfigurationLookup() {
        when(mockConfiguration.getConfiguredSubstituteImage(eq(ORIGINAL_IMAGE))).thenReturn(SUBSTITUTE_IMAGE);

        final DockerImageName substitute = underTest.apply(ORIGINAL_IMAGE);

        assertEquals("match is found", SUBSTITUTE_IMAGE, substitute);
        assertTrue("compatibility is automatically set", substitute.isCompatibleWith(ORIGINAL_IMAGE));
    }

    @Test
    public void testServiceLoaderFindsDefaultImplementation() {
        final ImageNameSubstitutor instance = ImageNameSubstitutor.instance();

        assertTrue(instance instanceof LogWrappedImageNameSubstitutor);
        assertTrue(((LogWrappedImageNameSubstitutor) instance).wrappedInstance instanceof DefaultImageNameSubstitutor);
    }
}
