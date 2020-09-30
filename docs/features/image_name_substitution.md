# Image name substitution

Testcontainers supports automatic substitution of Docker image names.

This allows replacement of an image name specified in test code with an alternative name - for example, to replace the 
name of a Docker Hub image dependency with an alternative hosted on a private image registry.

TODO:
 - [ ] explain deprecated use of image-by-image overrides in testcontainers.properties
 - [ ] explain prefix-based substitution (env var and testcontainers.properties)
 - [ ] explain how to write custom substitutors
