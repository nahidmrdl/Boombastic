package util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import util.ResourceCollection;
import util.ResourceCollection.Images;

public class ResourceCollectionTest {

    @Test
    void testImageLoading() {
        // Call the method to load the images
        ResourceCollection.readFiles();

        // Test each image to ensure it's not null
        for (Images image : Images.values()) {
            assertNotNull(image.getImage(), "Image " + image.name() + " not loaded");
        }
    }
}
