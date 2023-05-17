# Base image
FROM openjdk:11

RUN apt-get update && \
    apt-get install -y python3 python3-pip

# Install Firefox and the Selenium webdrivers for Chrome and Firefox
RUN apt-get install -y firefox-esr wget unzip && \
    wget -q https://github.com/mozilla/geckodriver/releases/download/v0.33.0/geckodriver-v0.33.0-linux64.tar.gz -O /tmp/geckodriver.tar.gz && \
    tar -xzf /tmp/geckodriver.tar.gz -C /usr/local/bin && \
    chmod +x /usr/local/bin/geckodriver

# Set up the path to the Firefox binary
ENV PATH="/usr/lib/firefox:${PATH}"
RUN mkdir -p /usr/lib/firefox
RUN ln -s /usr/bin/firefox-esr /usr/lib/firefox/firefox-bin


# Install Xvfb
RUN apt-get install -y xvfb



COPY src WebTester
COPY test WebTester
WORKDIR WebTester
RUN mkdir -p drivers
COPY drivers drivers
RUN mkdir -p faker
COPY faker faker
RUN mkdir -p selenium
COPY selenium selenium
RUN mkdir -p plugins
COPY plugins plugins
RUN mkdir -p bin
RUN javac -d build -cp .:faker/javafaker-1.0.2.jar:faker/automaton-1.11-8.jar:faker/commons-lang3-3.5.jar:faker/generex-1.0.2.jar:faker/snakeyaml-1.23-android.jar:drivers/webdrivermanager-5.3.3-fat.jar:selenium/selenium-server-4.9.0.jar:plugins/junit-platform-engine_1.9.2.jar:plugins/org.apiguardian.api_1.1.2.jar:plugins/junit-platform-launcher_1.9.2.jar:plugins/junit-jupiter-api_5.9.2.jar:plugins/junit-jupiter-engine_5.9.2.jar:plugins/junit-jupiter-migrationsupport_5.9.2.jar:plugins/junit-jupiter-params_5.9.2.jar:plugins/junit-platform-commons_1.9.2.jar:plugins/junit-platform-runner_1.9.2.jar:plugins/junit-platform-suite-api_1.9.2.jar:plugins/junit-platform-suite-commons_1.9.2.jar:plugins/junit-platform-suite-engine_1.9.2.jar:plugins/junit-vintage-engine_5.9.2.jar:plugins/org.hamcrest.core_1.3.0.v20180420-1519.jar:plugins/org.junit_4.13.2.v20211018-1956.jar:plugins/org.opentest4j_1.2.0.jar -d bin ./webTester/*.java
COPY Manifest.txt bin
COPY faker bin/faker
COPY selenium bin/selenium
COPY plugins bin/plugins
COPY drivers bin/drivers

# Set up the virtual display
ENV DISPLAY=:99
RUN Xvfb :99 -ac -screen 0 1280x1024x24 &

WORKDIR bin
RUN jar -cvfm Program.jar Manifest.txt ./webTester/*.class

CMD ["java","-cp", ".:faker/*:selenium/*:plugins/*:drivers/*:Program.jar","webTester.Program", "--headless"]