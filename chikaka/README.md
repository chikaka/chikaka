CHIKAKA

createdb -U postgres -E UTF8 chikaka
createdb -U postgres -E UTF8 chikakaTest

create extension pgcrypto;


<management>
    <security-realms>
        <security-realm name="ManagementRealm">
            <authentication>
                <local default-user="$local"/>
                <properties path="mgmt-users.properties" relative-to="jboss.server.config.dir"/>
            </authentication>
        </security-realm>
        <security-realm name="ApplicationRealm">
            <authentication>
                <jaas name="chikaka"/>
            </authentication>
        </security-realm>
    </security-realms>
    <management-interfaces>
        <native-interface security-realm="ManagementRealm">
            <socket-binding native="management-native"/>
        </native-interface>
        <http-interface security-realm="ManagementRealm">
            <socket-binding http="management-http"/>
        </http-interface>
    </management-interfaces>
</management>

<logger category="org.jboss.security">
    <level name="TRACE"/>
</logger>
<logger category="javax.security.auth">
    <level name="TRACE"/>
</logger>

<datasources>
    <datasource jta="false" jndi-name="java:jboss/datasources/chikaka" pool-name="chikaka" enabled="true" use-ccm="false">
        <connection-url>jdbc:postgresql://localhost:5432/chikaka</connection-url>
        <driver-class>org.postgresql.Driver</driver-class>
        <driver>postgresql-9.2-1002.jdbc4.jar</driver>
        <security>
            <user-name>postgres</user-name>
        </security>
        <validation>
            <validate-on-match>false</validate-on-match>
            <background-validation>false</background-validation>
        </validation>
        <statement>
            <share-prepared-statements>false</share-prepared-statements>
        </statement>
    </datasource>
    <datasource jta="false" jndi-name="java:jboss/datasources/chikakaTest" pool-name="chikakaTest" enabled="true" use-ccm="false">
        <connection-url>jdbc:postgresql://localhost:5432/chikakaTest</connection-url>
        <driver-class>org.postgresql.Driver</driver-class>
        <driver>postgresql-9.2-1002.jdbc4.jar</driver>
        <security>
            <user-name>postgres</user-name>
        </security>
        <validation>
            <validate-on-match>false</validate-on-match>
            <background-validation>false</background-validation>
        </validation>
        <statement>
            <share-prepared-statements>false</share-prepared-statements>
        </statement>
    </datasource>
    <drivers>
        <driver name="h2" module="com.h2database.h2">
            <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
        </driver>
    </drivers>
</datasources>

<security-domain name="chikaka" cache-type="default">
    <authentication>
        <login-module code="org.jboss.security.auth.spi.DatabaseServerLoginModule" flag="required">
            <module-option name="dsJndiName" value="java:jboss/datasources/chikaka"/>
            <module-option name="principalsQuery" value="select Password from SystemUser where UserName=?"/>
            <module-option name="rolesQuery" value="select sr.name, 'Roles' from SystemRole sr inner join SystemUser su on su.role = sr.id where su.UserName=?"/>
            <module-option name="hashAlgorithm" value="SHA-256"/>
            <module-option name="hashEncoding" value="Base64"/>
            <module-option name="hashCharset" value="UTF-8"/>
        </login-module>
    </authentication>
</security-domain>
