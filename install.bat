@echo off
echo Java keystore genereren...
keytool -keystore keystore.jks -genkey -keyalg RSA -alias dev
echo Java keystore verplaatsen...
move keystore.jks ./src/main/resources/keys/
echo Java keystore klaar, je kunt nu de applicatie starten.
echo:
echo Vergeet niet het wachtwoord wat je net hebt ingevoerd ook in te vullen in het application.properties bestand.