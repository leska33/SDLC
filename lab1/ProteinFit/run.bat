@echo off
cd /d "%~dp0"
if not exist build\classes-manual mkdir build\classes-manual
dir /s /b src\main\java\*.java > build\sources.txt
javac -encoding UTF-8 -d build\classes-manual @build\sources.txt
if errorlevel 1 (
  echo Compile failed
  pause
  exit /b 1
)
xcopy /E /I /Y src\main\resources\* build\classes-manual\ >nul
java -cp build\classes-manual com.example.proteinfit.ProteinFitApplication
