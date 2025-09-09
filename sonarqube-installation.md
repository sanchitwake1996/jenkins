# SonarQube Installation Steps 

## Install and configure Database
```shell
apt update
apt install openjdk-17-jdk -y
apt install postgresql -y
systemctl start postgresql
sudo -u postgres psql
>> CREATE USER linux PASSWORD 'redhat';
>> CREATE DATABASE sonarqube;
>> GRANT ALL PRIVILEGES ON DATABASE sonarqube TO linux;
>> \c sonarqube;
>> GRANT ALL PRIVILEGES ON SCHEMA public TO linux;
>> \q
```


## Configure Linux Machine
```shell
sysctl -w vm.max_map_count=524288
sysctl -w fs.file-max=131072
ulimit -n 131072
ulimit -u 8192
```


## Install and Configure Sonarqube
```shell
wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-25.5.0.107428.zip
apt install unzip -y
unzip sonarqube-25.5.0.107428.zip
mv sonarqube-25.5.0.107428 /opt/sonar
cd /opt/sonar
vim conf/sonar.properties
>> sonar.jdbc.username=linux
>> sonar.jdbc.password=redhat
>> sonar.jdbc.url=jdbc:postgresql://localhost/sonarqube
useradd sonar -m
chown sonar:sonar -R /opt/sonar
su sonar
cd /opt/sonar/bin/linux-x86-64
./sonar.sh start
./sonar.sh status 
```


sqp_2073e72c219177933a3e8c3f3ce983c1de746e0d

  stage('TEST') {
            steps {
                sh '''cd backend
                mvn sonar:sonar   -Dsonar.projectKey=studentapp   -Dsonar.projectName='studentapp'   -Dsonar.host.url=http://52.38.237.99:9000   -Dsonar.token=sqp_2073e72c219177933a3e8c3f3ce983c1de746e0d
                '''
            }
        }