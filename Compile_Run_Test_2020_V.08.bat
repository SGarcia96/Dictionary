@echo off

REM === Mensaje bienvenida
echo Se va a compilar la practica 2019/2020 con las dependencias y restricciones

REM === Inputs ===
set SRC_DIR=%cd%\src
set BIN_DIR=\bin
set MAIN=es/uned/lsi/eped/pract2019_2020/Main
set JAVA_HOME_JDK="C:\Program Files\Java\jdk-13.0.2"
set TMP_FOLDER=%cd%\juego_de_pruebas_2020\tmp

IF %JAVA_HOME_JDK%=="" (

	IF "%JAVA_HOME%" == "" (
	    echo Modifica la variable del archivo bat JAVA_HOME_JDK 
	    pause
	    exit
	) ELSE (
	    set JAVA_HOME_JDK="%JAVA_HOME%"
	)
)


REM === Mostramos variables ===
echo Carpeta codigo = %SRC_DIR%
echo Carpeta bin = %BIN_DIR%
echo Clase principal = %MAIN%
echo JAVA_HOME_JDK = %JAVA_HOME_JDK%
echo. 
echo.

REM === Clean and make temp dir ===
echo Limpiando compilacion anterior 
rd /q /s "%TMP_FOLDER%"
pause
if not exist "%TMP_FOLDER%" mkdir "%TMP_FOLDER%" 
mkdir "%TMP_FOLDER%%BIN_DIR%"
mkdir "%TMP_FOLDER%\src"
mkdir "%TMP_FOLDER%\src\es"
mkdir "%TMP_FOLDER%\src\es\uned"
mkdir "%TMP_FOLDER%\src\es\uned\lsi"
mkdir "%TMP_FOLDER%\src\es\uned\lsi\eped"
mkdir "%TMP_FOLDER%\src\es\uned\lsi\eped\pract2019_2020"
xcopy /s/q "%SRC_DIR%\es\uned\lsi\eped\pract2019_2020" "%TMP_FOLDER%\src\es\uned\lsi\eped\pract2019_2020"
echo.
echo.
pause
REM ===


REM === Comprobacion Estudiantes ===
echo Aplicando las restricciones a la practica
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2020/lib/Modifier.jar"  1

%JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2020/lib/Modifier.jar"  1

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2020/lib/Modifier.jar"  2

%JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2020/lib/Modifier.jar" 2

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo. 
echo.
pause
REM ===

REM === Compile ===
echo Compilando en carpeta temporal
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\javac" -d "%TMP_FOLDER%%BIN_DIR%" -sourcepath "%TMP_FOLDER%\src" -cp "juego_de_pruebas/lib/TAD_modified.jar" "%TMP_FOLDER%\src\%MAIN%.java"

%JAVA_HOME_JDK%"\bin\javac" -d "%TMP_FOLDER%%BIN_DIR%" -sourcepath "%TMP_FOLDER%\src" -cp "juego_de_pruebas_2020/lib/TAD_modified.jar" "%TMP_FOLDER%\src\%MAIN%.java"
if errorlevel 1 (
	echo Compilacion con errores
	pause
	exit /B 1
)

echo Compilacion sin errores
echo.
echo.
pause
REM ===

REM === Run Secuencia Estudiantes ===
echo Ejecutando el programa con busquedas de prueba para Estudiantes
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2020/lib/TAD_modified.jar" "%MAIN%" "juego_de_pruebas_2020/pruebas/DiccionarioEsp.txt" "juego_de_pruebas_2020/pruebas/BusquedasEstudiantes.txt" > "juego_de_pruebas_2020/salida/SalidaBusquedasEstudiantes.txt"

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2020/lib/TAD_modified.jar" "%MAIN%" "juego_de_pruebas_2020/pruebas/DiccionarioEsp.txt" "juego_de_pruebas_2020/pruebas/BusquedasEstudiantes.txt" > "juego_de_pruebas_2020/salida/SalidaBusquedasEstudiantes.txt"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo. 
echo.
pause

REM === Comprobacion Estudiantes ===
echo Comprobando bateria de pruebas para busquedas de Estudiantes
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2020/lib/Comparator.jar" "juego_de_pruebas_2020/salida/SalidaBusquedasEstudiantes.txt" "juego_de_pruebas_2020/salida/SalidaBusquedasEsperada.txt" "errores.txt"

%JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2020/lib/Comparator.jar" "juego_de_pruebas_2020/salida/SalidaBusquedasEstudiantes.txt" "juego_de_pruebas_2020/salida/SalidaBusquedasEsperada.txt" "errores.txt"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)
echo. 
echo.
pause