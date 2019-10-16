# Revision Log

## Example

```
{DATE}

{COMMIT MESSAGE}
{COMMIT MESSAGE}

{DATE}

{COMMIT MESSAGE}
...
```

## Date

**Format:** YYYY-MM-DD

## COMMIT MESSAGE

```
{VERSIONING} {ISSUE}
{SUMMARY}
```

## VERSIONING

```
{MAJOR.MINOR.BUGFIX.BUILD}
```

- **MAJOR:** major version
- **MINOR:** minor version
- **BUGFIX:** bug fix number
- **BUILD:** build number is sequencial (don't make part of a real versioning)

## SUMMARY:

```
- {VERB/ACTION} {CONTENT}
	- {ADDITIONAL OBSERVATIONS/COMMENTS}
```

- **Verb/Action:** added, fixed, upped, removed, switched, improved, updated, renamed
- **Content:** a message to describe a resume about what was made.
- **Additional Observations/Comments:** a *"free"* message to specify with more details what was made, use it on large and/or complex changes.

# Logs

## 2019-10-16

### 0.1.2.8
- Removed EcommerceLoggers and EcommerceLogger because will be used Log4J.

### 0.1.2.7
- Fixed pom maven 'artifactId' of 'commerce' to 'ecommerce-server'.

### 0.1.1.6
- Added pom maven settings:
    - 'url', 'organization.*'.
    - Properties to set build encode, junit/maven-build version.
    - JUnit dependencies.
    - Scarlet local dependencies 'scarlet-base' and 'scarlet-logger'.

## 2019-10-07

### 0.1.1.5
- Fixed @Autowired components not defined - EcommerceLoggers and EcommerceSettings.
- Fixed EcommerceLoggers that extends Loggers<EcommerceLogger> instead nothing.

### 0.1.0.4
- Changed project java version of 13 to 10.
- Updated maven project settings including spring dependencies.

### 0.1.0.3
- Changed project IDE of Eclipse to IntelliJ.

### 0.1.0.2
- Changed project IDE of Eclipse to IntelliJ.

### 0.1.0.1
- Initial project version.
- Added basic structures and configurations.
- Added provider web-service, service, model and repository.
