# Image Registry rate limiting

As of November 2020 Docker Hub pulls are rate limited. 
As Testcontainers uses Docker Hub for standard images, some users may hit these rate limits and should mitigate accordingly.

This page sets out recommended options for Testcontainers users.
Some combination of these approaches should be considered, taking into consideration the differences between local development and CI environments.

## Considerations for local dev vs CI

### Local development

## Options

### Do nothing

### Use an authenticated Docker Hub account

### Deploy a Registry server to act as a read-through cache

### Copy required images to a private registry and update references to images in test code

### Copy required images to a private registry and apply image name substitution
