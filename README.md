# Tubes PJM F Kel 2 – Pencarian Rute Terpendek

Proyek ini adalah aplikasi **GUI untuk mencari rute terpendek menuju bengkel**, menggunakan **algoritma Dijkstra** dengan menerapkan clean architecture.

reference algorithm from https://www.baeldung.com/java-dijkstra

# Struktur Project

```
── tubespjmfkel2
    ├── adapter
    │   └── DijkstraAdapter.java
    ├── algorithm
    │   ├── dijkstra
    │   │   └── Dijkstra.java
    │   └── graph
    │       ├── Graph.java
    │       └── Node.java
    ├── factory
    │   └── GraphFactory.java
    ├── gui
    │   ├── AppWindow.java
    │   └── graph
    │       ├── GraphManager.java
    │       ├── GraphPanel.java
    │       └── PathHighlighter.java
    └── Main.java
```

requirement: jdk 17 + jgraphx 3.9.3

# task

validasi

1. tambah jarak antar titiknya 1". jangan ditampung
2. cari rute terpendeknya 1". jangan ditampung

ui

1. tambah maps sebagai background
2. dll
