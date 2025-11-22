# Tubes PJM F Kel 2 – Pencarian Rute Terpendek

Proyek ini adalah aplikasi **GUI untuk mencari rute terpendek menuju bengkel**, menggunakan **algoritma Dijkstra** dengan menerapkan mvc arhitecture.

reference algorithm from https://www.baeldung.com/java-dijkstra

requirement: jdk 17 + jgraphx 3.9.3

# 📦 Struktur Project

```
tubespjmfkel2
├── controller
│   ├── DijkstraController.java          # Mengatur logika algoritma Dijkstra, komunikasi antara model dan view
│   └── GraphController.java             # Mengatur graf secara umum, menyimpan node, edge, dan menyediakan data untuk UI
├── dto
│   └── DijkstraResult.java              # Data Transfer Object untuk menampung hasil algoritma Dijkstra (path dan jarak)
├── Main.java                            # Titik masuk aplikasi, menjalankan AppWindow pada Event Dispatch Thread
├── model
│   ├── algorithm
│   │   └── Dijkstra.java                # Implementasi algoritma Dijkstra murni (logika perhitungan path terpendek)
│   └── entity
│       ├── Graph.java                   # Representasi struktur graf (vertex, edges)
│       └── Vertex.java                  # Representasi vertex pada graf
└── view
    ├── component
    │   └── GraphPanel.java              # Panel yang menampilkan graf menggunakan JGraphX
    ├── layout
    │   └── MainFrame.java               # Frame utama aplikasi, menampung GraphPanel dan kontrol GUI
    └── util
        └── PathHighlighter.java         # Utility untuk menyorot jalur tertentu pada graf (highlight path)
```

## 1. `Main.java`

Merupakan kelas awal eksekusi aplikasi (`entry point`).
Kelas ini hanya berisi method:

```java
public static void main(String[] args)
```

yang bertanggung jawab memulai tampilan utama (`MainFrame`).

---

## 2. Package `controller`

Berisi kelas–kelas yang menjalankan logika penghubung antara **model** dan **view**.
Controller bertindak sebagai jembatan yang memproses perintah pengguna, memanggil model, dan mengirimkan hasil kembali ke UI.

### 2.1 `GraphController.java`

-   Mengelola struktur graf (penambahan vertex, edge, dan perubahan data)
-   Menjadi sumber data untuk elemen visual graf
-   Menyediakan objek graf siap ditampilkan ke UI (misalnya untuk JGraphX)

### 2.2 `DijkstraController.java`

-   Mengatur proses pencarian jalur terpendek
-   Mengambil data dari `GraphController`
-   Memanggil algoritma Dijkstra pada model
-   Mengembalikan hasil dalam bentuk `DijkstraResult` untuk ditampilkan ke UI

---

## 3. Package `dto`

### `DijkstraResult.java`

-   Merupakan **Data Transfer Object**
-   Digunakan untuk menyimpan hasil eksekusi algoritma Dijkstra
-   Bersifat **immutable**
-   Berisi:

    -   `List<String> path` → urutan vertex hasil rute terpendek
    -   `int distance` → total jarak perjalanan

Objek ini memudahkan controller dan UI bertukar data tanpa harus memanipulasi model secara langsung.

---

## 4. Package `model`

Berisi seluruh **data dan logika murni aplikasi**, tanpa ketergantungan pada UI.

### 4.1 `model.entity`

#### `Vertex.java`

-   Representasi satu titik (vertex) pada graf
-   Menyimpan informasi berupa identitas atau nama vertex

#### `Graph.java`

-   Menyimpan koleksi vertex dan hubungan antar vertex (edge)
-   Menyediakan fungsi dasar seperti:

    -   Menambah vertex
    -   Menambah edge
    -   Mengambil bobot antar vertex
    -   Mengambil daftar tetangga

### 4.2 `model.algorithm`

#### `Dijkstra.java`

-   Implementasi algoritma Dijkstra murni
-   Tidak memiliki ketergantungan pada UI atau library eksternal
-   Menghasilkan:

    -   Jalur terpendek
    -   Total bobot perjalanan

-   Diakses melalui `DijkstraController`

---

## 5. Package `view`

Berisi seluruh tampilan pengguna (UI), komponen–komponen grafis, dan utilitas tampilan.

### 5.1 `view.layout`

#### `MainFrame.java`

-   Jendela utama aplikasi (`JFrame`)
-   Menampung:

    -   `GraphPanel`
    -   Tombol/perintah untuk menjalankan perhitungan rute

-   Tidak menyimpan logika bisnis

### 5.2 `view.component`

#### `GraphPanel.java`

-   Panel khusus yang menampilkan graf dalam bentuk visual
-   Menggunakan JGraphX (`mxGraphComponent`)
-   Mendukung refresh tampilan saat graf berubah

### 5.3 `view.util`

#### `PathHighlighter.java`

-   Kelas utilitas yang bertugas menyorot jalur (path) di graf
-   Digunakan setelah algoritma Dijkstra selesai menghasilkan rute
-   Tidak menyimpan data graf, hanya memanipulasi tampilan

---

# 🧠 Pola Arsitektur

Struktur ini mengikuti **pattern MVC (Model–View–Controller)**:

-   **Model**
    Berisi data inti dan algoritma (`Graph`, `Vertex`, `Dijkstra`)
-   **Controller**
    Mengatur aliran data dan eksekusi logika
-   **View**
    Menampilkan hasil ke pengguna melalui tampilan grafis

---

# task

ui

1. tambah maps sebagai background
2. dll

laporan
