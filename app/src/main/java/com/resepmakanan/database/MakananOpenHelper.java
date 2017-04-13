package com.resepmakanan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.resepmakanan.R;
import com.resepmakanan.model.Kategori;
import com.resepmakanan.model.Makanan;
import com.nekoloop.base64image.Base64Image;
import com.nekoloop.base64image.RequestEncode;

import java.util.ArrayList;

/**
 * Created by wahyu on 4/7/2017.
 */

public class MakananOpenHelper extends Database {
    private final Context context;
    private SQLiteDatabase mSQLLite;

    public MakananOpenHelper(Context context) {
        super(context);

        this.context = context;
    }

    @Override
    public void childOnCreate(SQLiteDatabase db) {
        db.execSQL("create table resep (" +
                "id integer primary key autoincrement," +
                "judul char(50) not null," +
                "gambar text," +
                "alat text not null," +
                "bahan text not null," +
                "langkah text not null," +
                "kategori char(15) not null," +
                "ic_favorite int not null" +
                ")");

        insertFirst();

    }

    private void insertFirst() {

   /*     Base64Image.with(context)
                .encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.makanan_ketupat_tahu))
                .into(new RequestEncode.Encode() {
                    @Override
                    public void onSuccess(String s) {
                        insert(
                                new Makanan(
                                        "",
                                        s,
                                        "",
                                        "",
                                        "",
                                        Kategori.MINUMAN
                                )
                        );
                    }

                    @Override
                    public void onFailure() {

                    }
                });*/

        Base64Image.with(context)
                .encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.es_yank_hun_semarang))
                .into(new RequestEncode.Encode() {
                    @Override
                    public void onSuccess(String s) {
                        insert(
                                new Makanan(
                                        "Es Yank Hun Semarang (untuk 6 orang)",
                                        s,
                                        "",
                                        "Bahan cendol:\n" +
                                                "1. 125g tepung beras\n" +
                                                "2. 25g tepung sagu\n" +
                                                "3. 250 ml air untuk melarutkan adonan\n" +
                                                "4. 1 sendok teh air kapur sirih\n" +
                                                "5. 300 ml air untuk dididihkan\n" +
                                                "Bahan kuah:\n" +
                                                "1. Sirup merah\n" +
                                                "2. Es batu",
                                        "\uF0B7 Aduk rata tepung beras, tepung sagu, air dan air kapur sirih.\n" +
                                                "\uF0B7 Didihkan air, masukkan campuran bahan cendol sambil diaduk hingga meletup-letup.\n" +
                                                "\uF0B7 Panas-panas tuang adonan ke dalam cetakan cendol,\n" +
                                                "\uF0B7 Tekan-tekan hingga adonan keluar berbentuk butiran-butiran bulat panjang, tampung\n" +
                                                "dalam wadah berisi air es.\n" +
                                                "\uF0B7 Sajikan cendol Yang Hun dengan sirup merah dan es batu.",
                                        Kategori.MINUMAN
                                )
                        );
                    }

                    @Override
                    public void onFailure() {

                    }
                });

        Base64Image.with(context)
                .encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.es_wus_wus))
                .into(new RequestEncode.Encode() {
                    @Override
                    public void onSuccess(String s) {
                        insert(
                                new Makanan(
                                        "Es Wus-Wus (Untuk 2 porsi)",
                                        s,
                                        "",
                                        "\uF0B7 2 sachet bubuk jeruk instan (masing-masing 11 gram)\n" +
                                                "\uF0B7 1 kaleng sprite\n" +
                                                "\uF0B7 100 gram jeruk sunkist, diiris tipis-tipis\n" +
                                                "\uF0B7 150 gram es batu",
                                        "1. Campur bubuk jeruk dengan sprite, aduk rata.\n" +
                                                "2. Tambahkan irisan jeruk sunkist dan es batu.\n" +
                                                "3. Sajikan dingin.",
                                        Kategori.MINUMAN
                                )
                        );
                    }

                    @Override
                    public void onFailure() {

                    }
                });

        Base64Image.with(context)
                .encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.es_tebak_dari_padang))
                .into(new RequestEncode.Encode() {
                    @Override
                    public void onSuccess(String s) {
                        insert(
                                new Makanan(
                                        "Es Tebak dari Padang (Untuk 8 porsi)",
                                        s,
                                        "",
                                        "Bahan Tebak:\n" +
                                                "1. 175 gram tepung beras\n" +
                                                "2. 30 gram tepung sagu\n" +
                                                "3. 500 ml air\n" +
                                                "4. 2 lembar daun pandan\n" +
                                                "Bahan Isi:\n" +
                                                "1. 200 gram kolang kaling\n" +
                                                "2. 100 gram gula pasir\n" +
                                                "3. 500 ml air\n" +
                                                "4. 3 tetes pewarna merah tua\n" +
                                                "Pelengkap:\n" +
                                                "1. 50 gram tape singkong\n" +
                                                "2. 200 gram cincau\n" +
                                                "3. 1/2 kaleng susu kental manis\n" +
                                                "4. Es serut\n" +
                                                "Bahan Kuah:\n" +
                                                "1. 1000 ml santan dari 1 butir kelapa\n" +
                                                "2. 2 lembar daun pandan\n" +
                                                "3. 1 1/2 sendok teh garam",
                                        "\uF0B7 Aduk bahan semua tebak, rebus hingga mendidih dan kental.\n" +
                                                "\uF0B7 Tuang ke dalam cetakan cendol.\n" +
                                                "\uF0B7 Tekan cetakan, lalu tampung cendol dalam air dingin.\n" +
                                                "\uF0B7 Rebus kolang kaling, air, gula, dan pewarna, sisihkan.Kuah:\n" +
                                                "\uF0B7 Rebus semua bahan, lalu dinginkan.\n" +
                                                "\uF076 Sajikan cendol tebak dengan kolang kaling dan bahan pelengkap lainnya dan kuah.",
                                        Kategori.MINUMAN
                                )
                        );
                    }

                    @Override
                    public void onFailure() {

                    }
                });

        Base64Image.with(context)
                .encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.es_kacang_merah_dari_manado))
                .into(new RequestEncode.Encode() {
                    @Override
                    public void onSuccess(String s) {
                        insert(
                                new Makanan(
                                        "Es Kacang Merah dari Manado (Untuk: 5 porsi)",
                                        s,
                                        "",
                                        "Bahan:\n" +
                                                "1. 150 gram kacang merah\n" +
                                                "2. 1 cm kayumanis\n" +
                                                "3. 1200 ml air\n" +
                                                "Bahan Sirup:\n" +
                                                "1. 100 gram gula merah\n" +
                                                "2. 125 gram gula pasir\n" +
                                                "3. 800 ml air\n" +
                                                "Pelengkap:\n" +
                                                "1. 1/2 kaleng susu kental manis\n" +
                                                "2. Es serut",
                                        "\uF0B7 Rebus kacang merah dan kayumanis sampai lunak. Tiriskan.\n" +
                                                "\uF0B7 Rebus bahan sirup hingga mendidih, lalu masukkan kacang merah.\n" +
                                                "\uF0B7 Sajikan dengan susu kental manis dan es serut.",
                                        Kategori.MINUMAN
                                )
                        );
                    }

                    @Override
                    public void onFailure() {

                    }
                });

        Base64Image.with(context)
                .encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.es_cendol_bandung))
                .into(new RequestEncode.Encode() {
                    @Override
                    public void onSuccess(String s) {
                        insert(
                                new Makanan(
                                        "Es Cendol Bandung Spesial",
                                        s,
                                        "",
                                        "Bahan:\n" +
                                                "1. 250gram tepung beras\n" +
                                                "2. 2 sendok makan tepung tapioka\n" +
                                                "3. 1 sendok teh pasta pandan\n" +
                                                "4. 1/2 sedndok teh garam\n" +
                                                "5. 500 ml air\n" +
                                                "6. Air matang secukupnya\n" +
                                                "7. Es batu secukpnya\n" +
                                                "Bahan Kuah:\n" +
                                                "1. 1 Liter santan encer\n" +
                                                "2. 100 gram gula pasir\n" +
                                                "Bahan Kinca:\n" +
                                                "1. 200 ml air\n" +
                                                "2. 500 gram gula merah, iris iris\n" +
                                                "3. 4 sendok makan gula pasir\n" +
                                                "4. 2 sendok teh tepung maizena, larutkan dengan sedikit air\n" +
                                                "5. 10 mata nangka potong dadu kecil kecil.\n" +
                                                "Bahan Pelengkap:\n" +
                                                "1. Es batu secukupnya\n" +
                                                "2. 200gram tapai ketan hitam",
                                        "Cendol :\n" +
                                                "\uF0B7 Campur tepung beras, tepung tapioka,pasta pandan, dan garam, seduh dengan sebagian\n" +
                                                "air\n" +
                                                "\uF0B7 Masak sisa air hingga mendidih, tuangkan larutan campuran tepung. aduk hingga rata,\n" +
                                                "masak degna api sedang\n" +
                                                "\uF0B7 Siapkan campuran es batu dan air matang dalam waadah, letakan cetakan cendol dengan\n" +
                                                "lubang agak besar di atasnya\n" +
                                                "\uF0B7 Tuang adonan cendol panas ke dalam cetakan, tekan tekan dengan sendok kayu hingga\n" +
                                                "butiran cendol keluar dan jatuh ke dalam air es, diamkan selama 1 jam hingga bentuknya\n" +
                                                "kokoh lalu saring dan tiriskan\n" +
                                                "Kuah :\n" +
                                                "\uF0B7 Campur semua bahan, masak di atas api kecil sambil terus di aduk agar tidak pecah\n" +
                                                "angkat dan saring\n" +
                                                "Kinca :\n" +
                                                "\uF0B7 Campur air, gula merah, dan gula pasir masak dengan api sedang hingga mendidih\n" +
                                                "masukan larutan maezena aaduk cepat angkat dan dinginkan setelah dingin masukan\n" +
                                                "potongan nangka\n" +
                                                "Cara Menyajikan :\n" +
                                                "\uF0B7 Siapkan gelas saji, masukan cendol, tambahkan tapia ketan, angka, kuah, kinca, dan es\n" +
                                                "batu secukupnya",
                                        Kategori.MINUMAN
                                )
                        );
                    }

                    @Override
                    public void onFailure() {

                    }
                });

        Base64Image.with(context)
                .encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.es_bubur_sumsum))
                .into(new RequestEncode.Encode() {
                    @Override
                    public void onSuccess(String s) {
                        insert(
                                new Makanan(
                                        "Es Bubur Sumsum (Untuk 10 porsi)",
                                        s,
                                        "",
                                        "Bahan Sumsum:\n" +
                                                "\uF0B7 150 gram tepung beras\n" +
                                                "\uF0B7 3/4 sendok teh garam\n" +
                                                "\uF0B7 ml santan, dari 1 1/2 butir kelapa\n" +
                                                "\uF0B7 3 lembar daun pandan, diikat simpul\n" +
                                                "Bahan Ketan Hitam:\n" +
                                                "\uF0B7 100 gram beras ketan hitam, direndam 2 jam\n" +
                                                "\uF0B7 750 ml air\n" +
                                                "\uF0B7 50 gram gula pasir\n" +
                                                "\uF0B7 1/4 sendok teh garam\n" +
                                                "\uF0B7 2 lembar daun pandan, diikat simpul\n" +
                                                "Bahan Pelengkap:\n" +
                                                "\uF0B7 250 gram cendol\n" +
                                                "\uF0B7 1 sendok makan selasih, diseduh\n" +
                                                "\uF0B7 gram es serut\n" +
                                                "Bahan Saus Santan:\n" +
                                                "\uF0B7 450 ml santan, dari 1/4 butir kelapa\n" +
                                                "\uF0B7 2 lembar daun pandan\n" +
                                                "\uF0B7 1/4 sendok teh garam\n" +
                                                "Bahan Air Gula:\n" +
                                                "\uF0B7 300 gram gula merah\n" +
                                                "\uF0B7 2 lembar daun pandan\n" +
                                                "\uF0B7 200 ml air",
                                        "Cara membuat Es Bubur Sum Sum Campur:\n" +
                                                "Bubur sumsum:\n" +
                                                "1. Larutkan tepung beras dan garam di dalam sebagian santan. Sisihkan.\n" +
                                                "2. Rebus sisa santan dan daun pandan sambil diaduk sampai mendidih.\n" +
                                                "3. Tuang larutan tepung beras. Aduk 20 menit sampai kental. Sisihkan.\n" +
                                                "Bubur ketan hitam:\n" +
                                                "1. Rebus ketan hitam, air, gula pasir, garam, dan daun pandan sambil diaduk sampai ketan hitam\n" +
                                                "matang dan kental. Sisihkan.\n" +
                                                "Saus santan:\n" +
                                                "1. Rebus santan, pandan, dan garam sambil diaduk hingga mendidih. Sisihkan.\n" +
                                                "Saus gula:\n" +
                                                "1. Rebus gula merah, pandan, dan garam. Masak sambil diaduk hingga mengental. Sisihkan.\n" +
                                                "\uF076 Tata bubur sumsum di mangkuk, tambahkan cendol, selasih, bubur ketan hitam, dan es serut.\n" +
                                                "Siramkan saus santan dan saus gula merah. Sajikan.",
                                        Kategori.MINUMAN
                                )
                        );
                    }

                    @Override
                    public void onFailure() {

                    }
                });

        Base64Image.with(context)
                .encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.buble_green_tea_berry))
                .into(new RequestEncode.Encode() {
                    @Override
                    public void onSuccess(String s) {
                        insert(
                                new Makanan(
                                        "Buble Green Tea Berry (Untuk 6 Porsi)",
                                        s,
                                        "",
                                        "Bahan:\n" +
                                                "1. 300g tepung tapioka\n" +
                                                "2. 1/2 sendok makan gula pasir\n" +
                                                "3. 1 sendok teh macha (teh hijau jepang)\n" +
                                                "4. 150ml air\n" +
                                                "Kuah:\n" +
                                                "1. 700ml susu cair\n" +
                                                "2. 100g stroberi, iris kecil\n" +
                                                "3. 10 sendok makan sirup stroberi\n" +
                                                "4. 2 kantung teh hijau, celupkan dalam 200ml air mendidih, dinginkan\n" +
                                                "5. 4 skop es krim stroberi\n" +
                                                "6. Es batu secukupnya",
                                        "Membuat Bubble:\n" +
                                                "\uF0B7 Didihkan air bersama macha dan gula.\n" +
                                                "\uF0B7 Taruh tapioka dalam wadah, masukkan air teh hijau panas sedikit demi sediki, aduk dengan\n" +
                                                "sendok kayu.\n" +
                                                "\uF0B7 Uleni hingga dapat dibentuk.\n" +
                                                "\uF0B7 Bentuk bola kecil-kecil. Didihkan air, masukkan bola teh dan rebus hingga mengapung.\n" +
                                                "\uF0B7 Angkat dan siram dengan air es.\n" +
                                                "Membuat Kuah:\n" +
                                                "\uF0B7 Ambil blender, masukkan semua bahan untuk kuah.\n" +
                                                "\uF0B7 Haluskan hingga bahan tercampur rata.\n" +
                                                "Tata bubble di dalam gelas saji, siram dengan kuah, aduk dan sajikan dengan sedotan yang besar.",
                                        Kategori.MINUMAN
                                )
                        );
                    }

                    @Override
                    public void onFailure() {

                    }
                });


        Base64Image.with(context)
                .encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.sop_buntut_bakso_udang))
                .into(new RequestEncode.Encode() {
                    @Override
                    public void onSuccess(String s) {
                        insert(
                                new Makanan(
                                        "Sup Buntut Bakso Udang",
                                        s,
                                        "",
                                        "Bahan:\n" +
                                                "1. 500 gram buntut sapi, rebus hingga empuk\n" +
                                                "2. 1 bungkus bakso udang\n" +
                                                "3. 800 ml air kaldu\n" +
                                                "4. 250 gram wortel, kupas, potong bulat besar\n" +
                                                "5. 3 buah oyong, kupas, potong bulat\n" +
                                                "6. 50 gram kapri, potong serong 2 bagian\n" +
                                                "7. 50 gram daun bawang, potong-potong\n" +
                                                "8. 1 batang seledri, potong-potong\n" +
                                                "9. 10 buah kentang kecil, kupas, goreng\n" +
                                                "10. 1 bbuah tomat, potong-potong\n" +
                                                "11. 1 sendok teh gula pasir\n" +
                                                "12. 1 sendok makan bawang merah goreng, untuk taburan\n" +
                                                "Bumbu Halus Sup Buntut Bakso Udang :\n" +
                                                "1. 1 sendok teh lada butir\n" +
                                                "2. 1/8 sendok teh pala\n" +
                                                "3. 1 sendok teh garam\n" +
                                                "4. 3 siung bawang putih",
                                        "Panaskan air kaldu, bumbu halus dan gula pasir hingga mendidih, masukkan buntut sapi dan\n" +
                                                "bakso udang, masak hingga matang.\n" +
                                                "Tambahkan wortel, oyong, kapri dan kentang kecil, aduk rata, masak hingga matang, angkat.\n" +
                                                "Masukkan irisan tomat, daun bawang, seledri dan bawang merah goreng, sajikan.",
                                        Kategori.MAKANAN
                                )
                        );
                    }

                    @Override
                    public void onFailure() {

                    }
                });


        Base64Image.with(context)
                .encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.belut_goreng_pedas))
                .into(new RequestEncode.Encode() {
                    @Override
                    public void onSuccess(String s) {
                        insert(
                                new Makanan(
                                        "Belut Goreng Pedas",
                                        s,
                                        "",
                                        "1. 1/2 kg belut\n" +
                                                "2. 10 buah bawang merah\n" +
                                                "3. 3 siung bawang putih\n" +
                                                "4. 5 buah cabe merah\n" +
                                                "5. 5 buah cabe rawit\n" +
                                                "6. 1 sendok teh asam jawa\n" +
                                                "7. 1 buah jeruk nipis, diambil airnya.\n" +
                                                "8. Garam, gula, dan minyak secukupnya",
                                        "\uF0B7 Belut yang sudah bersih, belah dan pukul-pukul, lumuri dengan jeruk, nipis dan gararn,\n" +
                                                "diamkan.\n" +
                                                "\uF0B7 Goreng belut sampai kering dan matang.\n" +
                                                "\uF0B7 Haluskan semua bumbu, tumis sampai harum.\n" +
                                                "\uF0B7 Masukkan belut, aduk sampai rata, angkat.",
                                        Kategori.MAKANAN
                                )
                        );
                    }

                    @Override
                    public void onFailure() {

                    }
                });


        Base64Image.with(context)
                .encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.kue_pie_stoberry))
                .into(new RequestEncode.Encode() {
                    @Override
                    public void onSuccess(String s) {
                        insert(
                                new Makanan(
                                        "Pie Stoberi",
                                        s,
                                        "",
                                        "Bahan kulit\n" +
                                                "\uF0B7 150 g tepung terigu\n" +
                                                "\uF0B7 75 g margarin\n" +
                                                "\uF0B7 25 g gula kastor\n" +
                                                "\uF0B7 1/4 sendok teh garam\n" +
                                                "\uF0B7 1 kuning telur\n" +
                                                "\uF0B7 1 sdm air\n" +
                                                "Bahan isi:\n" +
                                                "\uF0B7 220 g gula pasir\n" +
                                                "\uF0B7 6 sdm tepung maizena\n" +
                                                "\uF0B7 1 bungkus gelatin rasa strowberry\n" +
                                                "\uF0B7 500 ml air\n" +
                                                "\uF0B7 200 g strowberry, bersihkan",
                                        "Kulit :\n" +
                                                "1. Campur tepung terigu dengan margarin, gula, dan garam, aduk mempergunakan dua buah\n" +
                                                "pisau hingga berbutir halus seperti pasir.\n" +
                                                "2. Masukkan kuning telur dan air. aduk hingga menjadi adonan yang rata dan dapat di\n" +
                                                "pulung.\n" +
                                                "3. Tipiskan adonan hingga mencapai ketebalan kurang lebih 0,3 cm.\n" +
                                                "4. Siapkan cetakkan pai bongkar pasang berdiameter 15 cm, olesi margarin dan taburi\n" +
                                                "tepung terigu.\n" +
                                                "5. Letakkan adonan kulit pada dasar cetakkan. ratakan, panggang dalam oven panas bersuhu\n" +
                                                "200 derajat celcius selama kurang lebih 20 menit hingga matang. angkat. biarkan hingga\n" +
                                                "dingin.Isi:\n" +
                                                "1. Masak gula bersama tepung maizena, gelatin, dan air. aduk hingga mendidih dan kental.\n" +
                                                "angkat, diamkan hingga dingin.\n" +
                                                "2. Atur strowberry di atas kulit pai. siram dengan campuran gelatin. dinginkan dalam lemari\n" +
                                                "es. sajikan dengan hiasan whipped cream di atasnya.",
                                        Kategori.KUE
                                )
                        );
                    }

                    @Override
                    public void onFailure() {

                    }
                });

        Base64Image.with(context)
                .encode(BitmapFactory.decodeResource(context.getResources(), R.drawable.makanan_ketupat_tahu))
                .into(new RequestEncode.Encode() {
                    @Override
                    public void onSuccess(String s) {
                        insert(
                                new Makanan(
                                        "Ketupat Tahu",
                                        s,
                                        "",
                                        "Bahan Membuat Ketupat Tahu :\n" +
                                                "1. 10 buah ketupat\n" +
                                                "2. 10 potong (500gr) tahu kuning, digoreng\n" +
                                                "3. 3 butir telur rebus, diiris bundar\n" +
                                                "4. 250 gram toge panjang direbus sebentar\n" +
                                                "5. 2 sendok makan bawang goreng\n" +
                                                "6. 100 gram kerupuk merah\n" +
                                                "Bumbu kacang:\n" +
                                                "1. 250gram kacang tanah, digoreng\n" +
                                                "2. 100gram gula merah\n" +
                                                "3. 1 iris kecil kencur, digoreng\n" +
                                                "4. 1 sendok makan bawang putih goring\n" +
                                                "5. 100 ml air asam\n" +
                                                "6. 300ml santan\n" +
                                                "7. 5 sendok makan kecap manis\n" +
                                                "8. Garam secukupnya\n" +
                                                "9. Air jeruk limau sesuai selera\n" +
                                                "Sambal cabai rawit:\n" +
                                                "1. 20 buah cabai rawit, dihaluskan campur dengan garam.",
                                        "Cara Membuat Ketupat Tahu :\n" +
                                                "\uF0B7 Haluskan kacang tanah, gula merah, kencur dan bawang putih goreng dengan\n" +
                                                "menggunakan blender.\n" +
                                                "\uF0B7 Campur adonan kacang tanah yang telah dihaluskan dengan blender bersama air asam\n" +
                                                "dan santan, aduk hingga rata, lalu masak dalam panci hingga mendidih, tambahkan kecap\n" +
                                                "manis, garam dan air jeruk limau, aduk rata, angkat, sisihkan.\uF0B7 Ambil piring saji, letakkan ketupat, tahu, telur dan toge, lalu siram dengan bumbu\n" +
                                                "kacang, atasnya beri taburan bawang goreng dan kerupuk merah.\n" +
                                                "\uF0B7 Hidangkan bersama sambal rawit.",
                                        Kategori.MAKANAN
                                )
                        );
                    }

                    @Override
                    public void onFailure() {

                    }
                });
    }

    @Override
    public void childOnUpgrade(SQLiteDatabase db) {
        db.execSQL("drop table if exists resep");
        onCreate(db);
    }

    public boolean insert(Makanan makanan) {
        mSQLLite = this.getWritableDatabase();

        ContentValues mval = new ContentValues();
        mval.put(context.getString(R.string.database_resep_judul), makanan.getNama());
        mval.put(context.getString(R.string.database_resep_gambar), makanan.getGambar());
        mval.put(context.getString(R.string.database_resep_alat), makanan.getAlat());
        mval.put(context.getString(R.string.database_resep_bahan), makanan.getBahan());
        mval.put(context.getString(R.string.database_resep_langkah), makanan.getLangkah());
        mval.put(context.getString(R.string.database_resep_kategori), makanan.getKategori().toString());
        mval.put(context.getString(R.string.database_resep_favorite), String.valueOf(makanan.getFavorite()));

        Long i = mSQLLite.insert(context.getString(R.string.database_resep_table), null, mval);

        Log.d("Values of I = ", "******************* " + i + " ***************");

        mSQLLite.close();

        if (i == 1) {
            return true;
        }

        return false;
    }

    public boolean edit(Makanan makanan) {

        mSQLLite = this.getWritableDatabase();

        ContentValues mval = new ContentValues();
        mval.put(context.getString(R.string.database_resep_judul), makanan.getNama());
        mval.put(context.getString(R.string.database_resep_gambar), makanan.getGambar());
        mval.put(context.getString(R.string.database_resep_alat), makanan.getAlat());
        mval.put(context.getString(R.string.database_resep_bahan), makanan.getBahan());
        mval.put(context.getString(R.string.database_resep_langkah), makanan.getLangkah());
        mval.put(context.getString(R.string.database_resep_kategori), makanan.getKategori().toString());
        mval.put(context.getString(R.string.database_resep_favorite), String.valueOf(makanan.getFavorite()));

        return mSQLLite.update(context.getString(R.string.database_resep_table), mval, context.getString(R.string.database_resep_id) + " = " + String.valueOf(makanan.getId()), null) > 0;
    }

    public boolean delete(String id) {
        mSQLLite = this.getWritableDatabase();

        int returnDeleteInt = mSQLLite.delete(context.getString(R.string.database_resep_table), context.getString(R.string.database_resep_id) + " = " + id, null);

        mSQLLite.close();

        return returnDeleteInt > 0;
    }

    public Makanan select(String id) {
        mSQLLite = this.getWritableDatabase();
        Makanan makanan = null;

        Cursor alldata = mSQLLite.query(context.getString(R.string.database_resep_table),
                new String[]{
                        context.getString(R.string.database_resep_id),
                        context.getString(R.string.database_resep_judul),
                        context.getString(R.string.database_resep_gambar),
                        context.getString(R.string.database_resep_alat),
                        context.getString(R.string.database_resep_bahan),
                        context.getString(R.string.database_resep_langkah),
                        context.getString(R.string.database_resep_kategori),
                        context.getString(R.string.database_resep_favorite)
                }, context.getString(R.string.database_resep_id) + " = " + id, null, null, null, null);

        if (alldata != null) {
            alldata.moveToFirst();
            makanan = new Makanan(
                    alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_id))),
                    alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_judul))),
                    alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_gambar))),
                    alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_alat))),
                    alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_bahan))),
                    alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_langkah))),
                    Kategori.valueOf(alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_kategori)))),
                    alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_favorite)))
            );
        }

        mSQLLite.close();

        return makanan;
    }

    public ArrayList<Makanan> selectAll() {
        mSQLLite = this.getWritableDatabase();
        ArrayList<Makanan> makanans = new ArrayList<>();

        Cursor alldata = mSQLLite.query(context.getString(R.string.database_resep_table),
                new String[]{
                        context.getString(R.string.database_resep_id),
                        context.getString(R.string.database_resep_judul),
                        context.getString(R.string.database_resep_gambar),
                        context.getString(R.string.database_resep_alat),
                        context.getString(R.string.database_resep_bahan),
                        context.getString(R.string.database_resep_langkah),
                        context.getString(R.string.database_resep_kategori),
                        context.getString(R.string.database_resep_favorite)
                }, null, null, null, null, null);

        if (alldata.moveToFirst()) {
            while (!alldata.isAfterLast()) {
                Log.d("Values of alldata = ", "******************* " + DatabaseUtils.dumpCursorToString(alldata) + " ***************");

                Makanan makanan = new Makanan(
                        alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_id))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_judul))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_gambar))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_alat))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_bahan))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_langkah))),
                        Kategori.valueOf(alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_kategori)))),
                        alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_favorite)))
                );

                makanans.add(makanan);

                alldata.moveToNext();
            }
        }

        mSQLLite.close();

        return makanans;
    }

    public ArrayList<Makanan> selectAllFromKategori(String kategori) {
        mSQLLite = this.getWritableDatabase();
        ArrayList<Makanan> makanans = new ArrayList<>();

        Cursor alldata = mSQLLite.query(context.getString(R.string.database_resep_table),
                new String[]{
                        context.getString(R.string.database_resep_id),
                        context.getString(R.string.database_resep_judul),
                        context.getString(R.string.database_resep_gambar),
                        context.getString(R.string.database_resep_alat),
                        context.getString(R.string.database_resep_bahan),
                        context.getString(R.string.database_resep_langkah),
                        context.getString(R.string.database_resep_kategori),
                        context.getString(R.string.database_resep_favorite)
                }, context.getString(R.string.database_resep_kategori) + "  = \"" + kategori + "\"", null, null, null, null);

        if (alldata.moveToFirst()) {
            while (!alldata.isAfterLast()) {
                Log.d("Values of alldata = ", "******************* " + DatabaseUtils.dumpCursorToString(alldata) + " ***************");

                Makanan makanan = new Makanan(
                        alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_id))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_judul))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_gambar))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_alat))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_bahan))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_langkah))),
                        Kategori.valueOf(alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_kategori)))),
                        alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_favorite)))
                );

                makanans.add(makanan);

                alldata.moveToNext();
            }
        }

        mSQLLite.close();

        return makanans;
    }

    public ArrayList<Makanan> selectAllFavorite() {
        mSQLLite = this.getWritableDatabase();
        ArrayList<Makanan> makanans = new ArrayList<>();
        int a = 0;

        Cursor alldata = mSQLLite.query(context.getString(R.string.database_resep_table),
                new String[]{
                        context.getString(R.string.database_resep_id),
                        context.getString(R.string.database_resep_judul),
                        context.getString(R.string.database_resep_gambar),
                        context.getString(R.string.database_resep_alat),
                        context.getString(R.string.database_resep_bahan),
                        context.getString(R.string.database_resep_langkah),
                        context.getString(R.string.database_resep_kategori),
                        context.getString(R.string.database_resep_favorite)
                }, context.getString(R.string.database_resep_favorite) + " = 1", null, null, null, null);

        if (alldata.moveToFirst()) {
            while (!alldata.isAfterLast()) {
                Log.d("Values of alldata = ", "******************* " + DatabaseUtils.dumpCursorToString(alldata) + " ***************");

                Makanan makanan = new Makanan(
                        alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_id))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_judul))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_gambar))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_alat))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_bahan))),
                        alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_langkah))),
                        Kategori.valueOf(alldata.getString(alldata.getColumnIndex(context.getString(R.string.database_resep_kategori)))),
                        alldata.getInt(alldata.getColumnIndex(context.getString(R.string.database_resep_favorite)))
                );

                makanans.add(makanan);

                if (++a == 10) {
                    break;
                }

                alldata.moveToNext();
            }
        }

        mSQLLite.close();

        return makanans;
    }

}
