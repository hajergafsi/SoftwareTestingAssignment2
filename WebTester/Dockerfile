FROM openjdk:11

RUN apt-get update && \
    apt-get install -y wget unzip xvfb curl


RUN apt-get update && \
    apt-get install -y wget unzip xvfb

# Download and install Firefox
RUN wget -q "https://download.mozilla.org/?product=firefox-latest&os=linux64&lang=en-US" -O /tmp/firefox.tar.bz2 && \
    tar -xjf /tmp/firefox.tar.bz2 -C /opt/ && \
    ln -s /opt/firefox/firefox /usr/bin/firefox && \
    rm /tmp/firefox.tar.bz2

# Download and install geckodriver for Firefox
RUN wget -q https://github.com/mozilla/geckodriver/releases/download/v0.33.0/geckodriver-v0.33.0-linux64.tar.gz -O /tmp/geckodriver.tar.gz && \
    tar -xzf /tmp/geckodriver.tar.gz -C /usr/local/bin && \
    rm /tmp/geckodriver.tar.gz && \
    chmod +x /usr/local/bin/geckodriver

RUN curl -sS -o - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable

RUN CHROME_DRIVER_VERSION=$(curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE) && \
    wget -q "https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip" -O /tmp/chromedriver.zip && \
    unzip /tmp/chromedriver.zip -d /usr/local/bin && \
    rm /tmp/chromedriver.zip && \
    chmod +x /usr/local/bin/chromedriver



COPY src WebTester
COPY test WebTester

WORKDIR WebTester

RUN mkdir -p faker
COPY faker faker
RUN mkdir -p selenium
COPY selenium selenium
RUN mkdir -p plugins
COPY plugins plugins

RUN mkdir - p bin
RUN javac -d build -cp .:faker/javafaker-1.0.2.jar:faker/automaton-1.11-8.jar:faker/commons-lang3-3.5.jar:faker/generex-1.0.2.jar:faker/snakeyaml-1.23-android.jar:drivers/webdrivermanager-5.3.3-fat.jar:selenium/selenium-server-4.9.0.jar:plugins/junit-platform-engine_1.9.2.jar:plugins/org.apiguardian.api_1.1.2.jar:plugins/junit-platform-launcher_1.9.2.jar:plugins/junit-jupiter-api_5.9.2.jar:plugins/junit-jupiter-engine_5.9.2.jar:plugins/junit-jupiter-migrationsupport_5.9.2.jar:plugins/junit-jupiter-params_5.9.2.jar:plugins/junit-platform-commons_1.9.2.jar:plugins/junit-platform-runner_1.9.2.jar:plugins/junit-platform-suite-api_1.9.2.jar:plugins/junit-platform-suite-commons_1.9.2.jar:plugins/junit-platform-suite-engine_1.9.2.jar:plugins/junit-vintage-engine_5.9.2.jar:plugins/org.hamcrest.core_1.3.0.v20180420-1519.jar:plugins/org.junit_4.13.2.v20211018-1956.jar:plugins/org.opentest4j_1.2.0.jar -d bin ./webTester/*.java

COPY Manifest.txt bin
COPY faker bin/faker
COPY selenium bin/selenium
COPY plugins bin/plugins


WORKDIR bin

RUN jar -cvfm Program.jar Manifest.txt ./webTester/*.class

ENV DISPLAY=:99
RUN Xvfb :99 -ac -screen 0 1280x1024x24 &

CMD ["java", "-cp", ".:faker/*:selenium/*:plugins/*:Program.jar", "webTester.Program","--headless"]