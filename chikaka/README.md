CHIKAKA

TODO:

Configure the mail (Openshift ??):
<subsystem xmlns="urn:jboss:domain:mail:1.0">
    <mail-session jndi-name="java:jboss/mail/Default" >
        <smtp-server address="localhost" port="25"/>
    </mail-session>
    <mail-session jndi-name="java:/MyOtherMail">
        <smtp-server address="localhost" port="9999">
            <login name="nobody" password="pass"/>
        </smtp-server>
        <pop3-server address="example.com" port="1234"/>
        <imap-server address="example.com" port="432">
            <login name="nobody" password="pass"/>
        </imap-server>
    </mail-session>
</subsystem>
