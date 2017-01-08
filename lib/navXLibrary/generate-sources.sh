svn checkout --quiet --non-interactive --trust-server-cert https://github.com/kauailabs/navxmxp/trunk/roborio/java/navx_frc/src temporary-source-folder
zip -q -r navx_frc-sources.jar temporary-source-folder
rm -rf temporary-source-folder