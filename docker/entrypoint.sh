#!/bin/sh
CONFIG_DIR="/usr/local/tomcat/webapps/RDFV/WEB-INF/classes/config"
DEFAULT_CONFIG_DIR="/app/default-config"

if [ ! -f "$CONFIG_DIR/config.properties" ]; then
    echo "Warning! Not found file 'config.properties' - restoring default docker build file"
    cp "$DEFAULT_CONFIG_DIR/config.properties" "$CONFIG_DIR/config.properties"
fi

if [ ! -f "$CONFIG_DIR/predicates.properties" ]; then
    echo "Warning! Not found file 'predicates.properties' - restoring default docker build file"
    cp "$DEFAULT_CONFIG_DIR/predicates.properties" "$CONFIG_DIR/predicates.properties"
fi

if [ ! -f "$CONFIG_DIR/properties.xml" ]; then
    echo "Warning! Not found file 'properties.xml' - restoring default docker build file"
    cp "$DEFAULT_CONFIG_DIR/properties.xml" "$CONFIG_DIR/properties.xml"
fi

exec catalina.sh run
