environments {
    local {
        server {
            hostname = 'localhost'
            sshPort = 2222
            username = 'vagrant'
        }

        tomcat {
            hostname = '193.168.1.33'
            port = 8080
            context = 'todo'
        }
    }

    prod {
        server {
            hostname = '192.168.1.123'
            sshPort = 22
            username = 'root'
        }

        tomcat {
            hostname = '192.168.1.123'
            port = 8080
            context = 'todo'
        }
    }
}