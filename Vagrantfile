# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|


  config.vm.box = "ubuntu/xenial64"
  config.vm.box_version = "20180525.0.0"
  # Disable automatic box update checking. If you disable this, then
  # boxes will only be checked for updates when the user runs
  # `vagrant box outdated`. This is not recommended.
   config.vm.box_check_update = false

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine. In the example below,
  # accessing "localhost:8080" will access port 80 on the guest machine.
  # NOTE: This will enable public access to the opened port
  # config.vm.network "forwarded_port", guest: 80, host: 8080

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine and only allow access
  # via 127.0.0.1 to disable public access
  # config.vm.network "forwarded_port", guest: 80, host: 8080, host_ip: "127.0.0.1"

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  config.vm.network "private_network",
   #   name: "vboxnet0",
      ip: "192.168.56.10",
      netmask: "255.255.255.0"

  # Share an additional folder to the guest VM. The first argument is
  # the path on the guest to mount the folder. And the optional third
  # argument is a set of non-required options.
  config.vm.synced_folder "./vm_data", "/vagrant"

  # Provider-specific configuration so you can fine-tune various
  # backing providers for Vagrant. 
  config.vm.provider "virtualbox" do |vb|
  #   # Display the VirtualBox GUI when booting the machine
  #   vb.gui = true
  #
  #   # Customize the amount of memory on the VM:
     vb.gui = false
     vb.memory = 1024
     vb.cpus = 1
  end
  
  config.vm.provision "docker-installation", type: "shell", inline: <<-SHELL
  
     # Docker installation
     curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
     add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
     apt-get update
     apt-get install -y docker-ce
     
     # Create docker group and add the default user to it
     usermod -aG docker vagrant
  SHELL
  

  # Enable provisioning 
  config.vm.provision "docker" do |d|
  
  	
  end
end
