@echo off
setlocal enabledelayedexpansion

:: ======= CURVAS =======
set CURVA=secp521r1

:: ======= SIGNER KEYS =======
set SIGNER_SEC1=signer_ec_private_sec1.pem
set SIGNER_PRIV=signer_ec_private.pem
set SIGNER_PUB=signer_ec_public.pem
set SIGNER_PRIV_B64=signer_ec_private_base64.txt
set SIGNER_PUB_B64=signer_ec_public_base64.txt

:: ======= ENCRYPTION KEYS =======
set ENC_SEC1=encrypt_ec_private_sec1.pem
set ENC_PRIV=encrypt_ec_private.pem
set ENC_PUB=encrypt_ec_public.pem
set ENC_PRIV_B64=encrypt_ec_private_base64.txt
set ENC_PUB_B64=encrypt_ec_public_base64.txt

echo [*] Limpiando archivos anteriores...
for %%F in (%SIGNER_SEC1% %SIGNER_PRIV% %SIGNER_PUB% %SIGNER_PRIV_B64% %SIGNER_PUB_B64% ^
             %ENC_SEC1% %ENC_PRIV% %ENC_PUB% %ENC_PRIV_B64% %ENC_PUB_B64%) do (
    del /f /q %%F >nul 2>&1
)

:: ======= GENERAR CLAVES PARA FIRMAR =======
echo [*] Generando clave privada ECC (firma)...
openssl ecparam -genkey -name %CURVA% -noout -out %SIGNER_SEC1%

echo [*] Convirtiendo clave privada de firma a formato PKCS#8...
openssl pkcs8 -topk8 -nocrypt -in %SIGNER_SEC1% -out %SIGNER_PRIV%

echo [*] Generando clave pública de firma...
openssl ec -in %SIGNER_SEC1% -pubout -out %SIGNER_PUB%

echo [*] Codificando clave privada de firma en Base64 (una sola línea)...
set "all="
for /f "usebackq delims=" %%A in (`openssl base64 -in %SIGNER_PRIV%`) do (
    set "line=%%A"
    set "all=!all!!line!"
)
echo !all! > %SIGNER_PRIV_B64%
set all=

echo [*] Codificando clave pública de firma en Base64 (una sola línea)...
set "all="
for /f "usebackq delims=" %%A in (`openssl base64 -in %SIGNER_PUB%`) do (
    set "line=%%A"
    set "all=!all!!line!"
)
echo !all! > %SIGNER_PUB_B64%
set all=

:: ======= GENERAR CLAVES PARA CIFRADO =======
echo [*] Generando clave privada ECC (cifrado)...
openssl ecparam -genkey -name %CURVA% -noout -out %ENC_SEC1%

echo [*] Convirtiendo clave privada de cifrado a formato PKCS#8...
openssl pkcs8 -topk8 -nocrypt -in %ENC_SEC1% -out %ENC_PRIV%

echo [*] Generando clave pública de cifrado...
openssl ec -in %ENC_SEC1% -pubout -out %ENC_PUB%

echo [*] Codificando clave privada de cifrado en Base64 (una sola línea)...
set "all="
for /f "usebackq delims=" %%A in (`openssl base64 -in %ENC_PRIV%`) do (
    set "line=%%A"
    set "all=!all!!line!"
)
echo !all! > %ENC_PRIV_B64%
set all=

echo [*] Codificando clave pública de cifrado en Base64 (una sola línea)...
set "all="
for /f "usebackq delims=" %%A in (`openssl base64 -in %ENC_PUB%`) do (
    set "line=%%A"
    set "all=!all!!line!"
)
echo !all! > %ENC_PUB_B64%
set all=

:: ======= LIMPIAR ARCHIVOS SEC1 (opcionales) =======
del /f /q %SIGNER_SEC1% >nul 2>&1
del /f /q %ENC_SEC1% >nul 2>&1

echo.
echo [✓] Proceso completo. Archivos generados:
echo.
echo [Firma - Signer]
echo     - Privada PKCS#8:      %SIGNER_PRIV%
echo     - Pública:             %SIGNER_PUB%
echo     - Privada Base64:      %SIGNER_PRIV_B64%
echo     - Pública Base64:      %SIGNER_PUB_B64%
echo.
echo [Cifrado - Encryption]
echo     - Privada PKCS#8:      %ENC_PRIV%
echo     - Pública:             %ENC_PUB%
echo     - Privada Base64:      %ENC_PRIV_B64%
echo     - Pública Base64:      %ENC_PUB_B64%
echo.

endlocal
pause
