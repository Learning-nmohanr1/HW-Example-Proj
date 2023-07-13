# Helloworld
hellowordproject


## Build & Deploy

This project requires **JDK 17** to build and run the application.
>If your workstation is not already setup with Java 17, then you can refer to [Java 17 Workstation Setup](https://github.ford.com/DevEnablement/pcfdev-guides/blob/master/migrations/docs/Java-17.md#workstation-setup).


It also requires **gcloud** CLI if you want to deploy to GCP Cloud Run manually. You can visit [GCP Docs]([go.ford/dev-onboard](https://docs.gcp.ford.com/docs/support/local-dev/)) page for links to download these resources.

When using Tekton, make sure you are using Java/JRE 17 container images in your tasks. Refer available versions of container images [here](https://github.ford.com/DevEnablement/container-images).

### Run & Test Locally from command line

To run locally you must use the following Gradle commands to first compile and then run the project:
1. To compile (and run included JUnit tests) run the following from command line:
   ```
   ./gradlew build
   ```
2. To run the project (this starts Spring Boot's embedded Tomcat server) run the following from command line:
   ```
   ./gradlew bootRun
   ```

> Note: Windows users do not use the `./` in front of the `gradlew` commands. The instructions assume Mac command prompt

Few important notes about running locally:

* Your local app runs on port 8080, by default. If you have enabled actuator endpoint, you can test if the app is running:
  [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)


* REST endpoints provided in this app are secured with OAuth2 and Azure AD. To test, check API specification locally and provide tokens in 'Authorization' and 'X-Forwarded-Authorization' headers when invoking API with Postman or CURL. Refer to **Test Client credentials** section below for information on generating tokens.
> Note: If you try to access APIs without providing tokens, it will return '401 Unauthorized' or '403 Forbidden'.

> Note: Postman and/or CURL are preferred options to test secured endpoints, over Swagger UI.

* To stop your running app, press `^C` (control-C). This kills the process.

## IDE

Your IDE version must support Java **17** &mdash; i.e. Eclipse (>= 2018-09), IntelliJ (>= 2018.2).
**You must install and enable use of Lombok for your IDE.** Lombok setup instructions can be found [here](https://github.ford.com/DevEnablement/pcfdev-guides/tree/master/base-service#lombok).

### Deploy to GCP CloudRun manually using gcloud CLI

In order to deploy, you must first build (`./gradlew build`).

To deploy your built application:

```bash
# login into your GCP Project
gcloud auth login --impersonate-service-account=[service-account-with-deploy-permission]

# build an image using JIB
./gradlew jib --image=<IMAGE_NAME>

# deploy with gcloud command
gcloud run deploy service-name --image=[IMAGE_NAME]
```

- For more image build options, refer to [Google JIB Gradle plugin doc](https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin#quickstart).

- For more deploy options, refer to [Google Cloud reference doc](https://cloud.google.com/sdk/gcloud/reference/run/deploy). 

- To test Cloud Run service using Postman or CURL, see section **Testing API deployed to GCP Cloud Run from local machine**



## Swagger UI and API Specification
This generated Spring Boot application includes Swagger UI and API Specification docs.

> Note: EcoBoost generated apps, Dev Enablement's frameworks and libraries are now using springdoc-openapi (instead of springfox) for API Spec.

Running locally with default configuration,
- Swagger UI URL - http://localhost:8080/swagger-ui/index.html
- API Docs URL - http://localhost:8080/v3/api-docs
  
## Tekton - Application CI/CD Pipeline
You can easily create, update OR upgrade a Tekton Pipeline for this application in your OpenShift namespace, using the [EcoBoost Tekton CI/CD Pipeline Generator](https://devservices.ford.com/tekton-pipeline). 

Read the comprehensive [Tekton Pipeline Dev Guide](https://github.ford.com/DevEnablement/ecoboost-tekton-pipeline#how-to-use-it) to understand the prerequisites and pipeline configurations.



## Additional Recommendations
### GitHub Branch Protection
Code that is deployed to a production environment (Application, Website, Phone, Vehicle, Data Center, etc…) must have Branch Protection 
turned on in GitHub. This is required to comply with ISP Segregation of Duty policies.

Read more about best practices and recommendations to enforce branch protection rules in GitHub repository, [here](https://github-guide.ford.com/ch06/04-branch-protection.html).

### Secrets Management
Credentials, tokens, keys, passwords, and secrets must not be stored in GitHub.

Read more about how to use GCP Secrets Manager for storing app secrets, [here](https://github.ford.com/DevEnablement/gcp-devguides/tree/master/secrets-manager).

### Segregation of Duties (SoD)
Read more about SoD recommendations for Tekton, [here](https://github.ford.com/DevEnablement/ecoboost-tekton-pipeline/blob/master/README.md#securing-tekton-pipeline-access).

Read more about SoD recommendations in general, [here](https://github-guide.ford.com/ch06/02-separation-duty.html).

## EcoBoost Project Features
This [**EcoBoost Project**](https://go.ford/spring-ecoboost) was originally generated with the following features:

- [**Core Features**](https://github.ford.com/DevEnablement/pcfdev-guides/blob/master/base-service/README.md#core-features) &mdash;
  Spring Boot 3.1
  , Gradle 7
  , Java 17
  , [Gradle Boost Plugin](https://github.ford.com/DevEnablement/gradle-boost-plugin)
  , [Lombok](https://projectlombok.org/)
  , Firewall Friendly
  , Proxy Aware

- [**Recommended Web Features**](https://github.ford.com/DevEnablement/pcfdev-guides/tree/master/base-service#recommended-web-features) &mdash;
  Swagger
  , Actuator
  , Sleuth
  , X-Application-Info
  , X-Request-Info
  , Common Error Handling
  

- Other Features & Reference Code &mdash;
  
  , [Ecoboost Tekton Pipeline](https://github.ford.com/DevEnablement/ecoboost-tekton-pipeline) - Refer the dev-guide for Tekton pipeline configuration steps.

- [42Crunch Audit & Conformance Compliance](https://azureford.sharepoint.com/sites/atfordit/SitePages/EcoBoostEnables42Crunch.aspx)

- [API Catalog / APIGEE publish ready](https://github.ford.com/Platform-Enablement/Catalog-and-Publisher/wiki)

- **OSS Vulnerability free** - scanned with [FOSSA](https://ford.fossa.com/) and regularly patched for CVEs

- **Quality Code** - scanned with [Checkmarx](https://www.checkmarx.ford.com/CxWebClient/ProjectState.aspx) and [SonarQube](https://www.sonarqube.ford.com)

## Dev Enablement Resources
Need to learn more about your EcoBoost project or learn more about additional integrations available for your Spring
Boot app? See our Dev Guides, Community Forum, and other resources in our Dev Services portal
at [https://devservices.ford.com](https://devservices.ford.com).


## Stay Informed of New Releases
Use the GitHub Watch Button to be notified of releases to repos associated with our EcoBoost product:
* [EcoBoost SpringBoot App](https://github.ford.com/DevEnablement/pcfdev-service-spring-ecoboost)
* [Spring Base Dependencies](https://github.ford.com/DevEnablement/spring-base-dependencies)
* [Gradle Boost plugin](https://github.ford.com/DevEnablement/gradle-boost-plugin)
* [EcoBoost Jenkins Pipeline](https://github.ford.com/DevEnablement/ecoboost-pipeline)
* [EcoBoost Tekton Pipeline](https://github.ford.com/DevEnablement/ecoboost-tekton-pipeline/)

## Contact Us
Need to notify us of a bug, have issues, new feature request or simply want to brag? 
Give us feedback 
- https://devservices.ford.com/
Join the /d/c/s Community Channels!
- [/Dev/Central/Station Webex Teams](https://www.webexteams.ford.com/space?r=fz8y)
