BA-moire
========

Bachelorarbeit zu Moiré-Effekten beim Domain-Coloring. Siehe "text/thesis.pdf".
Im Ordner MoireApplet befinden sich die Quelldateien des Programms MOIGEN.

MOIGEN
------
Das Programm MOIGEN hat den Zweck, den Anwender mit der Entstehung von Moiré-Mustern im Bildbereich und den entsprechenden Impulsen im Frequenzspektrum vertraut zu machen. Dafür können Liniengitter und Raster beliebiger Breite und Strichstärke übereinandergelegt und gegeneinander verdreht werden. 

Das Programm besteht aus einem Hauptfenster, zu sehen in Abb. 1, in dem Ebenen hinzugefügt und gelöscht, sowie den eigenen Vorstellungen angepasst werden können. Außerdem besteht die Möglichkeit ein bereits bestehendes Szenario “Preset” zu laden. Dazu kommen drei Anzeigefenster: eine Tabelle (Abb. 2) mit Einträgen zu Strichstärke, Drehwinkel, etc. für jede Ebene, das Bildfenster (Abb. 3), in dem die Überlagerung der Bilder angezeigt wird und das Bildspektrumfenster (Abb. 4), das nur manchmal aktiviert ist. 

Der Anwender kann beliebig viele Ebenen übereinanderlegen und sie mit dem Haken links entweder aktivieren oder deaktivieren. Dabei kann er die Typen “LINES” oder “DOTS” und die Formen “STRAIGHT”, “PARABOLA” und “CIRCLE” frei miteinander kombinieren. Für die Form “STRAIGHT” wird das Bildspektrumfenster aktiviert. 

Darin kann man das Frequenzspektrum der Ebene beobachten. Der Kreis in der Mitte ist der visibility circle. Die durchgezogenen Linien stehen für die Impulskämme der ursprünglichen Bilder, Punkte repräsentieren Impulse, wobei die der ursprünglichen Bilder schwarz, neu hinzugekommene dagegen rosa sind. Ihre Farbintensität hängt von der Stärke des Impulses ab.

![Hauptfenster](grafiken/hauptfenster.png?raw=true "Das Hauptfenster. Im Moment sind zwei Ebenen vorhanden")
Abb.1: Das Hauptfenster. Im Moment sind zwei Ebenen vorhanden.


![Uebersicht](grafiken/uebersicht.png?raw=true "Eine Tabelle zur Übersicht über die gewählten Einstellungen")
Abb.2: Eine Tabelle zur Übersicht über die gewählten Einstellungen.

![Bildfenster](grafiken/bildfenster.png?raw=true "Das Bildfenster")
Abb.3: Das Bildfenster.

![Spektrumfenster](grafiken/spektrumfenster.png?raw=true "Das Spektrumfenster")
Abb.4: Das Bildspektrumfenster, links im deaktvierten Zustand.
