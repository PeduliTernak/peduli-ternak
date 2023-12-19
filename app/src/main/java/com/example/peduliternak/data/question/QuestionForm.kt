package com.example.peduliternak.data.question

data class QuestionForm(
    val id: Int,
    val text: String
)

class QuestionRepository {
    val questions: List<QuestionForm> = listOf(
        QuestionForm(
            id = 1,
            text = "Apakah Anda pernah mengamati perubahan warna pada air susu sapi menjadi kekuning-kuningan?"
        ),
        QuestionForm(
            id = 2,
            text = "Apakah sapi mengalami batuk?"
        ),
        QuestionForm(
            id = 3,
            text = "Apakah suhu tubuh sapi rendah?"
        ),QuestionForm(
            id = 4,
            text = "Apakah bulu sapi terlihat kusam?"
        ),
        QuestionForm(
            id = 5,
            text = "Apakah mulut sapi kering?"
        ),
        QuestionForm(
            id = 6,
            text = "Apakah dibagian perut sapi anda terlihat cekung(legok) ?"
        ),
        QuestionForm(
            id = 7,
            text = "Apakah sapi Anda sering menggigit bagian tubuhnya?"
        ),
        QuestionForm(
            id = 8,
            text = "Apakah sapi anda terlihat gemetaran ?"
        ),
        QuestionForm(
            id = 9,
            text = "Apakah sapi anda mengeluarkan air liur yang berlebihan?"
        ),
        QuestionForm(
            id = 10,
            text = "pada sapi betina, Apakah pernah terjadi kejadian di mana sapi kehilangan kehamilan pada usia 6-9 bulan?"
        ),
        QuestionForm(
            id = 11,
            text = "apakah61?"
        ),

        //penyakit 1
        QuestionForm(
            id = 12,
            text = "Apakah frekuensi pernafasan sapi meningkat, di luar rentang normal 24-42 kali/menit?"
        ),
        QuestionForm(
            id = 13,
            text = "Apakah sapi mengalami penurunan nafsu makan?"
        ),
        QuestionForm(
            id = 14,
            text = "Apakah sapi mengalami dehidrasi?"
        ),
        QuestionForm(
            id = 15,
            text = "Apakah sapi terlihat lemah dan kurang aktif?"
        ),
        QuestionForm(
            id = 16,
            text = "Apakah sapi gelisah, sering bangun dan duduk kembali secara berulang, serta sensitif terhadap interaksi dengan manusia?"
        ),
        QuestionForm(
            id = 17,
            text = "Apakah produksi susu sapi menurun di bawah normal sekitar 12-15 liter/hari?"
        ),
        QuestionForm(
            id = 18,
            text = "Apakah sapi mengalami demam?"
        ),
        QuestionForm(
            id = 19,
            text = "Apakah pernah ada kasus susu sapi yang menggumpal pada sapi Anda?"
        ),
        QuestionForm(
            id = 20,
            text = "Apakah ada keluhan pada puting sapi, seperti keluarnya darah dan nanah? Apakah ada tanda-tanda infeksi?"
        ),
        QuestionForm(
            id = 21,
            text = "Apakah ada perubahan pada bentuk / ukuran pada ambing sapi? Apakah ada tanda-tanda peradangan?"
        ),
        QuestionForm(
            id = 22,
            text = "Apakah ambing sapi terasa sakit dan panas saat disentuh?"
        ),

//penyakit 2
        QuestionForm(
            id = 23,
            text = "Apakah sapi mengalami demam?"
        ),
        QuestionForm(
            id = 24,
            text = "Apakah frekuensi pernafasan sapi meningkat, di luar rentang normal 24-42 kali/menit?"
        ),
        QuestionForm(
            id = 25,
            text = "Apakah sapi mengalami penurunan nafsu makan?"
        ),
        QuestionForm(
            id = 26,
            text = "Apakah sapi mengalami dehidrasi?"
        ),
        QuestionForm(
            id = 27,
            text = "Apakah sapi terlihat lemah dan kurang aktif?"
        ),
        QuestionForm(
            id = 28,
            text = "Apakah sapi gelisah, sering bangun dan duduk kembali secara berulang, serta sensitif terhadap interaksi dengan manusia?"
        ),
        QuestionForm(
            id = 29,
            text = "Apakah terdapat keluarnya cairan atau lendir dari hidung sapi?"
        ),

//penyakit 3
        QuestionForm(
            id = 30,
            text = "Apakah sapi mengalami penurunan nafsu makan?"
        ),
        QuestionForm(
            id = 31,
            text = "Apakah sapi gelisah, sering bangun dan duduk kembali secara berulang, serta sensitif terhadap interaksi dengan manusia?"
        ),
        QuestionForm(
            id = 32,
            text = "Apakah sapi terlihat beringas?"
        ),
        QuestionForm(
            id = 33,
            text = "Akhir-akhir ini, Apakah  sapi Anda pernah tidak buang air kecil dan buang air besar sama sekali?"
        ),
        QuestionForm(
            id = 34,
            text = "Apakah sapi Anda sulit bergerak dan berdiri(terlihat pincang)?"
        ),
        QuestionForm(
            id = 35,
            text = "Apakah kaki belakang sapi Anda terlihat lemah dan sulit bergerak?"
        ),
        QuestionForm(
            id = 36,
            text = "Apakah kaki dan telinga sapi Anda dingin?"
        ),
        QuestionForm(
            id = 37,
            text = "Akhir-akhir ini, Apakah sapi Anda tertidur/beristirahat dengan berbaring pada salah satu sisi?"
        ),
        QuestionForm(
            id = 38,
            text = "Apakah kepala sapi Anda mengarah ke belakang membentuk huruf “S”?"
        ),
        QuestionForm(
            id = 39,
            text = "Apakah Anda mengamati bahwa sapi kurang responsif terhadap lingkungan sekitar dan ada perubahan perilaku yang mencolok?"
        ),

//penyakit 4
        QuestionForm(
            id = 40,
            text = "Apakah sapi mengalami penurunan nafsu makan?"
        ),
        QuestionForm(
            id = 41,
            text = "Apakah sapi mengalami dehidrasi?"
        ),
        QuestionForm(
            id = 42,
            text = "Apakah sapi terlihat lemah dan kurang aktif?"
        ),
        QuestionForm(
            id = 43,
            text = "Apakah terdapat keluarnya cairan atau lendir dari hidung sapi?"
        ),
        QuestionForm(
            id = 44,
            text = "Apakah sapi mengalami keluarnya cairan dari mata?"
        ),
        QuestionForm(
            id = 45,
            text = "Apakah sapi mengalami bulu rontok?"
        ),
        QuestionForm(
            id = 46,
            text = "Apakah kulit dan bulu sapi terasa kaku?"
        ),
        QuestionForm(
            id = 47,
            text = "Apakah tubuh sapi anda terlihat kurus (tulang rusuk hampir terlihat) ?"
        ),
        QuestionForm(
            id = 48,
            text = "Akhir-akhir ini, Apakah  sapi Anda buang air besar lebih sering dari biasanya?"
        ),
        QuestionForm(
            id = 49,
            text = "Apakah kotoran sapi Anda lembek,berwarna gelap, disertai lendir sampai cair?"
        ),
        QuestionForm(
            id = 50,
            text = "Apakah ada darah atau cacing di kotoran sapi Anda?"
        ),
        QuestionForm(
            id = 51,
            text = "Apakah sapi Anda sering merengek atau merintih?"
        ),
        QuestionForm(
            id = 52,
            text = "Akhir-akhir ini, Apakah sapi Anda berjalan sempoyongan bahkan sampai pernah ambruk?"
        ),
        QuestionForm(
            id = 53,
            text = "Apakah punggung sapi terlihat melengkung?"
        ),

//penyakit 5
        QuestionForm(
            id = 54,
            text = "Apakah frekuensi pernafasan sapi meningkat, di luar rentang normal 24-42 kali/menit?"
        ),
        QuestionForm(
            id = 55,
            text = "Apakah sapi mengalami penurunan nafsu makan?"
        ),
        QuestionForm(
            id = 56,
            text = "Apakah sapi mengalami dehidrasi?"
        ),
        QuestionForm(
            id = 57,
            text = "Apakah sapi terlihat lemah dan kurang aktif?"
        ),
        QuestionForm(
            id = 58,
            text = "apakah10"
        ),
        QuestionForm(
            id = 59,
            text = "Apakah detak jantung sapi meningkat di luar rentang normal sekitar 60-70 kali/menit?"
        ),
        QuestionForm(
            id = 60,
            text = "Apakah sapi mengalami suhu tubuh naik dan turun?"
        ),
        QuestionForm(
            id = 61,
            text = "Apakah sapi mengalami bulu rontok?"
        ),
        QuestionForm(
            id = 62,
            text = "Apakah kulit dan bulu sapi terasa kaku?"
        ),
        QuestionForm(
            id = 63,
            text = "Apakah tubuh sapi anda terlihat kurus (tulang rusuk hampir terlihat) ?"
        ),
        QuestionForm(
            id = 64,
            text = "Akhir-akhir ini, Apakah  sapi Anda buang air besar lebih sering dari biasanya?"
        ),
        QuestionForm(
            id = 65,
            text = "Akhir-akhir ini, Apakah sapi Anda berjalan sempoyongan bahkan sampai pernah ambruk?"
        ),
        QuestionForm(
            id = 66,
            text = "Apakah telinga sapi Anda terkulai(Tergantung lemah)"
        ),
        QuestionForm(
            id = 67,
            text = "Akhir-akhir ini, Apakah mata sapi Anda terlihat mengantuk (mata suram dan cekung) ?"
        ),

//penyakit 6
        QuestionForm(
            id = 68,
            text = "Apakah frekuensi pernafasan sapi meningkat, di luar rentang normal 24-42 kali/menit?"
        ),
        QuestionForm(
            id = 69,
            text = "Apakah sapi mengalami penurunan nafsu makan?"
        ),
        QuestionForm(
            id = 70,
            text = "Apakah sapi terlihat lemah dan kurang aktif?"
        ),
        QuestionForm(
            id = 71,
            text = "Apakah sapi gelisah, sering bangun dan duduk kembali secara berulang, serta sensitif terhadap interaksi dengan manusia?"
        ),
        QuestionForm(
            id = 72,
            text = "apakah10"
        ),
        QuestionForm(
            id = 73,
            text = "Apakah akhir-akhir ini sapi anda berat badannya menurun?"
        ),
        QuestionForm(
            id = 74,
            text = "Apakah dibagian perut sapi anda terlihat cekung(legok)?"
        ),
        QuestionForm(
            id = 75,
            text = "Akhir-akhir ini, Apakah sapi anda pernah muntah?"
        ),
        QuestionForm(
            id = 76,
            text = "Akhir-akhir ini, Apakah  sapi anda buang air kecil lebih sering dari biasanya?"
        ),
        QuestionForm(
            id = 77,
            text = "Akhir-akhir ini, Apakah sapi Anda sering menghentakkan kakinya dan sering mengais-ais perutnya?"
        ),
        QuestionForm(
            id = 78,
            text = "Apakah sapi sering bernafas dengan mulut terbuka dan ada tanda-tanda kesulitan bernafas?"
        ),
        QuestionForm(
            id = 79,
            text = "Apakah  sapi lebih cenderung melakukan perilaku memanjangkan leher?"
        ),

//penyakit 7
        QuestionForm(
            id = 80,
            text = "Apakah sapi mengalami bulu rontok?"
        ),
        QuestionForm(
            id = 81,
            text = "Apakah kulit dan bulu sapi terasa kaku?"
        ),
        QuestionForm(
            id = 82,
            text = "Apakah Anda pernah melihat sapi menggosok-gosokkan badannya pada kandang dan ada area tertentu yang tampak sakit atau gatal?"
        ),
        QuestionForm(
            id = 83,
            text = "Apakah ada bagian tubuh sapi yang mengeluarkan nanah?"
        ),
        QuestionForm(
            id = 84,
            text = "Apakah Anda pernah melihat sapi dengan gejala timbulnya kerak berwarna abu-abu atau keropeng pada kulit?"
        ),

//penyakit 8
        QuestionForm(
            id = 85,
            text = "Apakah sapi mengalami demam?"
        ),
        QuestionForm(
            id = 86,
            text = "Apakah frekuensi pernafasan sapi meningkat, di luar rentang normal 24-42 kali/menit?"
        ),
        QuestionForm(
            id = 87,
            text = "Apakah sapi mengalami penurunan nafsu makan?"
        ),
        QuestionForm(
            id = 88,
            text = "Apakah sapi mengalami dehidrasi?"
        ),
        QuestionForm(
            id = 89,
            text = "Apakah sapi terlihat lemah dan kurang aktif?"
        ),
        QuestionForm(
            id = 90,
            text = "Apakah terdapat keluarnya cairan atau lendir dari hidung sapi?"
        ),
        QuestionForm(
            id = 91,
            text = "Apakah sapi mengalami keluarnya cairan dari mata?"
        ),
        QuestionForm(
            id = 92,
            text = "Apakah detak jantung sapi meningkat di luar rentang normal sekitar 60-70 kali/menit?"
        ),
        QuestionForm(
            id = 93,
            text = "Apakah produksi susu sapi menurun di bawah normal sekitar 12-15 liter/hari?"
        ),
        QuestionForm(
            id = 94,
            text = "apakah20"
        ),
        QuestionForm(
            id = 95,
            text = "Apakah sapi Anda sulit bergerak dan berdiri(terlihat pincang)?"
        ),

//penyakit 9
        QuestionForm(
            id = 96,
            text = "Apakah sapi mengalami demam?"
        ),
        QuestionForm(
            id = 97,
            text = "Apakah sapi anda mengalami melepuh pada mulut dan kaki?"
        ),
        QuestionForm(
            id = 98,
            text = "Apakah sapi anda mengalami luka pada kuku dan kukunya lepas?"
        ),
        QuestionForm(
            id = 99,
            text = "Apakah sapi mengalami penurunan nafsu makan?"
        ),

//penyakit 10
        QuestionForm(
            id = 100,
            text = "Apakah Anda pernah melihat pendarahan keluar dari vagina sapi?"
        ),
        QuestionForm(
            id = 101,
            text = "Apakah ada kasus kehilangan kehamilan berulang pada sapi dalam kurun waktu 2 tahun setelah kehilangan kehamilan sebelumnya?"
        ),
        QuestionForm(
            id = 102,
            text = "Apakah Anda pernah mengamati ada tanda-tanda gangguan pada alat reproduksi sapi?"
        ),
        QuestionForm(
            id = 103,
            text = "Apakah Anda pernah mengamati adanya pembesaran pada kantong persendian sapi, atau gejala yang dikenal sebagai higroma? Dan apakah terdapat tanda-tanda peradangan atau ketidaknyamanan pada area tersebut?"
        ),

        //penyakit 11
        QuestionForm(
            id = 104,
            text = "Apakah sapi gelisah, sering bangun dan duduk kembali secara berulang, serta sensitif terhadap interaksi dengan manusia?"
        ),
        QuestionForm(
            id = 105,
            text = "Apakah produksi susu sapi menurun di bawah normal sekitar 12-15 liter/hari?"
        ),
        QuestionForm(
            id = 106,
            text = "Apakah akhir-akhir ini sapi anda berat badannya menurun ?"
        ),
        QuestionForm(
            id = 107,
            text = "Akhir-akhir ini, Apakah  sapi Anda buang air besar lebih sering dari biasanya?"
        ),

    )
}
