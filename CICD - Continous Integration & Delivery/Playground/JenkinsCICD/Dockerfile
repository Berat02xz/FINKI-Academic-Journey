FROM jenkins/jenkins:lts

USER root

# Install necessary tools and dependencies
RUN apt-get update && \
    apt-get install -y sudo && \
    apt-get clean

# Add a user to the 'sudo' group and allow passwordless sudo
RUN usermod -aG sudo jenkins && \
    echo "jenkins ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers

USER jenkins