# Pathology Information System

GitHub Link: https://github.com/fabianxvogt/PathologyInformationSystem

## Aufgabenstellung

Wir wollen ein minimales PIS als Konsolenanwendung programmieren, dass einen kleinen Teil der
üblichen Funktionen zur Verfügung stellt. Auch thematisch werden wir uns eingrenzen und
beschäftigen uns ausschließlich mit der Diagnostik des Prostatakarzinoms. Unser Konsolen-PIS soll
folgende Menüpunkte bereitstellen:
1. Eingehenden Fall aufnehmen: Im Laboreingang wird sämtliches ankommendes Material
erfasst und als Fall angelegt. In unserem PIS gehören hierzu Name, Vorname, Anschrift,
Krankenkasse, behandelnde Ärztin sowie Art des Materials (Biopsie oder Resektat).
2. Fall im Labor bearbeiten: Zunächst wird eine Liste aller noch nicht bearbeiteter Fälle
angezeigt. Anhand der Nummer kann der zu bearbeitende Fall gewählt werden. Handelt es
sich bei dem Material um eine Biopsie, so wird in der Anwendung erfasst, wie viele Schnitte
erzeugt wurden und wie sie jeweils gefärbt wurden. Bei einem Resektat (also nach einer
radikalen Prostatektomie) soll der Zuschnitt dokumentiert werden. Hierbei sollen jeweils die
Blöcke aus Apex und Basis sowie die Scheiben mitsamt entsprechender Unterteilung erfasst
werden.
3. Fall exportieren: Zunächst wird eine Liste aller Fälle angezeigt, die bereits im Labor
bearbeitet (sprich zugeschnitten und entsprechend dokumentiert) wurden. Anhand der
Nummer kann der zu exportierende Fall ausgewählt werden. Anschließend wird eine JSONDatei generiert, in der alle Informationen zum Fall sowie bei Resektaten zusätzlich der
Zuschnitt enthalten sind.
4. Fall analysieren: Fall exportieren: Zunächst wird eine Liste aller Fälle angezeigt, die bereits im
Labor bearbeitet (sprich zugeschnitten und entsprechend dokumentiert) wurden. Anhand
der Nummer kann der zu analysierende Fall ausgewählt werden. Anschließend soll eine
Analyse ausgegeben werden, die die Anzahl der Objektträger des Falls sowie bei Resektaten
zusätzlich die berechnete durchschnittliche Scheibendicke enthält.


## Programm ausführen

1. mvn compile
2. mvn exec:java


## Funktionen

#### 1. Neue Aerzt*in einstellen
- Einstellen neuer Mitarbeiter

#### 2.	Aerzteliste
- Vollständige Liste aller Mitarbeiter

#### 3.	Neue Patient*in aufnehmen
- Aufnehmen neuer Patienten

#### 4.	Patientenliste
- Vollständige Liste aller Patienten

#### 5.	Neuen Fall erfassen
- Erstellung eines neuen Falls (Biopsie oder Resektat)
- Bedingung: Zuerst muss mindestens ein Arzt eingestellt sein

#### 6.	Fall im Labor bearbeiten
- Hiermit können Fälle, die sich im Status 'neu' befinden, im Labor bearbeitet und dokumentiert werden
- Biopsie: Dokumentation der Schnittfarben
- Resektat: Dokumentation der einzelnen Stücke und ihrer Objektträger
- Anschließend wechselt der Fall-Status in 'bearbeitet'

#### 7.	Fall exportieren
- Export von Fällen in JSON

#### 8.	Fall analysieren
- Detailinformationen zu Fällen, welche sich im Status 'bearbeitet' befinden

#### 9.	Beenden
- Beendet das Programm
