package com.resepmakanan.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.DaoException;

/**
 * Created by wahyu on 4/7/2017.
 */
@Entity
public class Makanan {
    @Id(autoincrement = true)
    private Long id;

    private String judul;
    private String gambar;
    private String langkah;
    private String bahan;

    @NotNull
    private long id_kategori;

    @ToOne(joinProperty = "id_kategori")
    private Kategori kategori;

    private int favorite;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 421227369)
    private transient MakananDao myDao;

    @Generated(hash = 282039875)
    private transient Long kategori__resolvedKey;


    @Generated(hash = 618772091)
    public Makanan() {
    }

    public Makanan(String judul, String gambar, String bahan, String langkah, Kategori kategori) {
        this.judul = judul;
        this.gambar = gambar;
        this.langkah = langkah;
        this.bahan = bahan;
        this.kategori = kategori;
    }

   
    public Makanan(Long id, String judul, String gambar, String langkah, String bahan, Long id_kategori,
            int favorite) {
        this.id = id;
        this.judul = judul;
        this.gambar = gambar;
        this.langkah = langkah;
        this.bahan = bahan;
        this.id_kategori = id_kategori;
        this.favorite = favorite;
    }

    @Generated(hash = 1503015454)
    public Makanan(Long id, String judul, String gambar, String langkah, String bahan, long id_kategori,
            int favorite) {
        this.id = id;
        this.judul = judul;
        this.gambar = gambar;
        this.langkah = langkah;
        this.bahan = bahan;
        this.id_kategori = id_kategori;
        this.favorite = favorite;
    }


    public void changeFavorite() {
        if (favorite == 0) {
            favorite = 1;
        } else {
            favorite = 0;
        }
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGambar() {
        return this.gambar;
    }


    public void setGambar(String gambar) {
        this.gambar = gambar;
    }


    public String getLangkah() {
        return this.langkah;
    }


    public void setLangkah(String langkah) {
        this.langkah = langkah;
    }


    public String getBahan() {
        return this.bahan;
    }


    public void setBahan(String bahan) {
        this.bahan = bahan;
    }


    public int getFavorite() {
        return this.favorite;
    }


    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 739329279)
    public void setKategori(@NotNull Kategori kategori) {
        if (kategori == null) {
            throw new DaoException(
                    "To-one property 'id_kategori' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.kategori = kategori;
            id_kategori = kategori.getId();
            kategori__resolvedKey = id_kategori;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }


    public Long getId_kategori() {
        return this.id_kategori;
    }

    public void setId_kategori(Long id_kategori) {
        this.id_kategori = id_kategori;
    }

    /** To-one relationship, resolved on first access. */
    @Keep
    public Kategori getKategori() {
        Long __key = this.id_kategori;

        if (kategori !=null){
            return kategori;
        }

        if (kategori__resolvedKey == null || !kategori__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            KategoriDao targetDao = daoSession.getKategoriDao();
            Kategori kategoriNew = targetDao.load(__key);
            synchronized (this) {
                kategori = kategoriNew;
                kategori__resolvedKey = __key;
            }
        }
        return kategori;
    }

    public void setId_kategori(long id_kategori) {
        this.id_kategori = id_kategori;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1896011773)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMakananDao() : null;
    }
}
