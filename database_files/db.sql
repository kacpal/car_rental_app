/*
Created: 26.11.2021
Modified: 09.12.2021
Project: Wypożyczalnia pojazdów
Model: Wypożyczalnia pojazdów
Company: WEiTI, Politechnika Warszawska
Author: Szymon Włoczewski, Kacper Paluch
Version: 2.0
Database: Oracle 19c
*/


-- Create sequences section -------------------------------------------------

CREATE SEQUENCE WypozyczalniaSeq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE AdresSeq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE StacjaSeq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE WlascicielSeq
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE PocztaSeq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE PracownikSeq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE KlientSeq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE PojazdSeq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE ModelSeq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE TelefonSeq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE MarkaSeq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE StanowiskoSeq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE JezykSeq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

-- Create tables section -------------------------------------------------

-- Table Wypozyczalnie

CREATE TABLE Wypozyczalnie(
  Nr_wypozyczalni Integer NOT NULL,
  Nazwa Varchar2(30 ) NOT NULL,
  Nr_adresu Integer NOT NULL
)
/

-- Create indexes for table Wypozyczalnie

CREATE INDEX IX_adres_wypozyczalni ON Wypozyczalnie (Nr_adresu)
/

-- Add keys for table Wypozyczalnie

ALTER TABLE Wypozyczalnie ADD CONSTRAINT Unique_Identifier1 PRIMARY KEY (Nr_wypozyczalni)
/

-- Table Pracownicy

CREATE TABLE Pracownicy(
  Nr_pracownika Integer NOT NULL,
  Imie Varchar2(20 ) NOT NULL,
  Nazwisko Varchar2(30 ) NOT NULL,
  Data_urodzenia Date NOT NULL,
  PESEL Varchar2(11 ),
  Plec Char(1 ) NOT NULL
        CHECK (Plec in ('K', 'M')),
  Nr_wypozyczalni Integer NOT NULL,
  Nr_stanowiska Integer NOT NULL,
  Nr_adresu Integer NOT NULL
)
/

-- Create indexes for table Pracownicy

CREATE INDEX IX_wypozyczalnia_pracownika ON Pracownicy (Nr_wypozyczalni)
/

CREATE INDEX IX_stanowisko_pracownika ON Pracownicy (Nr_stanowiska)
/

CREATE INDEX IX_adres_pracownika ON Pracownicy (Nr_adresu)
/

-- Add keys for table Pracownicy

ALTER TABLE Pracownicy ADD CONSTRAINT Unique_Identifier2 PRIMARY KEY (Nr_pracownika)
/

-- Table Klienci

CREATE TABLE Klienci(
  Id_klienta Integer NOT NULL,
  Imie Varchar2(20 ) NOT NULL,
  Nazwisko Varchar2(30 ) NOT NULL,
  Data_urodzenia Date NOT NULL,
  Nr_prawa_jazdy Varchar2(30 ) NOT NULL,
  Nr_dokumentu_tozsamosci Varchar2(30 ) NOT NULL,
  Nr_adresu Integer NOT NULL
)
/

-- Create indexes for table Klienci

CREATE INDEX IX_adres_klienta ON Klienci (Nr_adresu)
/

-- Add keys for table Klienci

ALTER TABLE Klienci ADD CONSTRAINT Unique_Identifier3 PRIMARY KEY (Id_klienta)
/

-- Table Pojazdy

CREATE TABLE Pojazdy(
  Nr_pojazdu Integer NOT NULL,
  Generacja Varchar2(20 ),
  Rok_produkcji Integer NOT NULL,
  Nr_rejestracyjny Varchar2(7 ),
  Rodzaj_paliwa Varchar2(15 ) NOT NULL
        CHECK (Rodzaj_paliwa IN ('Diesel','Benzyna','Elektryczny')),
  Ilosc_miejsc Integer NOT NULL,
  Nr_wypozyczalni Integer NOT NULL,
  Nr_modelu Integer NOT NULL
)
/

-- Create indexes for table Pojazdy

CREATE INDEX IX_pojazd_wypozyczalni ON Pojazdy (Nr_wypozyczalni)
/

CREATE INDEX IX_model_pojazdu ON Pojazdy (Nr_modelu)
/

-- Add keys for table Pojazdy

ALTER TABLE Pojazdy ADD CONSTRAINT Unique_Identifier4 PRIMARY KEY (Nr_pojazdu)
/

-- Table Sprzedawcy

CREATE TABLE Sprzedawcy(
  Nr_pracownika Integer NOT NULL,
  Tytul Varchar2(10 ) NOT NULL
        CHECK (Tytul IN ('junior', 'mid', 'senior'))
)
/

-- Add keys for table Sprzedawcy

ALTER TABLE Sprzedawcy ADD CONSTRAINT Unique_Identifier5 PRIMARY KEY (Nr_pracownika)
/

-- Table Kierowcy

CREATE TABLE Kierowcy(
  Nr_pracownika Integer NOT NULL,
  Nr_prawo_jazdy Varchar2(30 ) NOT NULL,
  Data_waznosci_prawa_jazdy Date,
  Data_waznosci_badan Date NOT NULL
)
/

-- Add keys for table Kierowcy

ALTER TABLE Kierowcy ADD CONSTRAINT Unique_Identifier6 PRIMARY KEY (Nr_pracownika)
/

-- Table Samochody_osobowe

CREATE TABLE Samochody_osobowe(
  Nr_pojazdu Integer NOT NULL,
  GPS Char(1 ) NOT NULL,
  Skrzynia_biegow Varchar2(20 ) NOT NULL
        CHECK (Skrzynia_biegow IN ('Automatyczna','Manualna')),
  Ilosc_drzwi Integer NOT NULL,
  Nadwozie Varchar2(10 ) NOT NULL
        CHECK (Nadwozie IN ('Sedan','SUV','Compact','Coupe','Hatchback','Sport coupe','Van','Pickup')),
  Naped Varchar2(3 ) NOT NULL
        CHECK (Naped IN ('RWD','FWD','AWD'))
)
/

-- Add keys for table Samochody_osobowe

ALTER TABLE Samochody_osobowe ADD CONSTRAINT Unique_Identifier7 PRIMARY KEY (Nr_pojazdu)
/

-- Table Samochody_ciezarowe

CREATE TABLE Samochody_ciezarowe(
  Nr_pojazdu Integer NOT NULL,
  Pojemnosc_bagaznika Integer NOT NULL,
  DMC Integer NOT NULL,
  Dlugosc Float NOT NULL,
  Szerokosc Float NOT NULL,
  Wysokosc Float NOT NULL
)
/

-- Add keys for table Samochody_ciezarowe

ALTER TABLE Samochody_ciezarowe ADD CONSTRAINT Unique_Identifier9 PRIMARY KEY (Nr_pojazdu)
/

-- Table Stacje_benzynowe

CREATE TABLE Stacje_benzynowe(
  Nr_stacji Integer NOT NULL,
  Liczba_instrybutorow Integer NOT NULL,
  Nr_wypozyczalni Integer NOT NULL
)
/

-- Create indexes for table Stacje_benzynowe

CREATE INDEX IX_posiada_stacje ON Stacje_benzynowe (Nr_wypozyczalni)
/

-- Add keys for table Stacje_benzynowe

ALTER TABLE Stacje_benzynowe ADD CONSTRAINT Unique_Identifier11 PRIMARY KEY (Nr_stacji)
/

-- Table Adresy

CREATE TABLE Adresy(
  Nr_adresu Integer NOT NULL,
  Miasto Varchar2(20 ) NOT NULL,
  Ulica Varchar2(20 ) NOT NULL,
  Nr_lokalu Varchar2(4 ) NOT NULL,
  Nr_poczty Integer NOT NULL
)
/

-- Create indexes for table Adresy

CREATE INDEX IX_poczta_adresu ON Adresy (Nr_poczty)
/

-- Add keys for table Adresy

ALTER TABLE Adresy ADD CONSTRAINT PK_Adresy PRIMARY KEY (Nr_adresu)
/

-- Table Poczty

CREATE TABLE Poczty(
  Nr_poczty Integer NOT NULL,
  Kod_poczty Char(6 ) NOT NULL,
  Poczta Varchar2(20 ) NOT NULL
)
/

-- Add keys for table Poczty

ALTER TABLE Poczty ADD CONSTRAINT PK_Poczty PRIMARY KEY (Nr_poczty)
/

-- Table Stanowiska

CREATE TABLE Stanowiska(
  Nr_stanowiska Integer NOT NULL,
  Nazwa_stanowiska Varchar2(30 ) NOT NULL,
  Opis Varchar2(400 ) NOT NULL
)
/

-- Add keys for table Stanowiska

ALTER TABLE Stanowiska ADD CONSTRAINT PK_Stanowiska PRIMARY KEY (Nr_stanowiska)
/

ALTER TABLE Stanowiska ADD CONSTRAINT Nr_stanowiska UNIQUE (Nr_stanowiska)
/

-- Table Wypozyczenia

CREATE TABLE Wypozyczenia(
  Data_wypozyczenia Date NOT NULL,
  Id_klienta Integer NOT NULL,
  Nr_pojazdu Integer NOT NULL,
  Data_zwrotu Date
)
/

-- Add keys for table Wypozyczenia

ALTER TABLE Wypozyczenia ADD CONSTRAINT PK_Wypozyczenia PRIMARY KEY (Id_klienta,Nr_pojazdu,Data_wypozyczenia)
/

-- Table Telefony

CREATE TABLE Telefony(
  Id_telefonu Integer NOT NULL,
  Nr_telefonu Varchar2(15 ) NOT NULL,
  Czy_glowny Char(1 ) NOT NULL,
  Nr_pracownika Integer NOT NULL,
  Id_klienta Integer
)
/

-- Create indexes for table Telefony

CREATE INDEX IX_telefon_pracownika ON Telefony (Nr_pracownika)
/

CREATE INDEX IX_telefon_klienta ON Telefony (Id_klienta)
/

-- Add keys for table Telefony

ALTER TABLE Telefony ADD CONSTRAINT PK_Telefony PRIMARY KEY (Id_telefonu)
/

-- Table Znajomosc_jezykow

CREATE TABLE Znajomosc_jezykow(
  Nr_jezyka Integer NOT NULL,
  Nr_pracownika Integer NOT NULL,
  Kod_poziomu Varchar2(2 ) NOT NULL
)
/

-- Add keys for table Znajomosc_jezykow

ALTER TABLE Znajomosc_jezykow ADD CONSTRAINT PK_Znajomosc_jezykow PRIMARY KEY (Nr_jezyka,Nr_pracownika)
/

-- Table Jezyki

CREATE TABLE Jezyki(
  Nr_jezyka Integer NOT NULL,
  Kod_jezyka Varchar2(3 ) NOT NULL,
  Nazwa Varchar2(20 ) NOT NULL
)
/

-- Add keys for table Jezyki

ALTER TABLE Jezyki ADD CONSTRAINT PK_Jezyki PRIMARY KEY (Nr_jezyka)
/

ALTER TABLE Jezyki ADD CONSTRAINT Kod_jezyka UNIQUE (Kod_jezyka)
/

-- Table Modele

CREATE TABLE Modele(
  Nr_modelu Integer NOT NULL,
  Nazwa Varchar2(30 ) NOT NULL,
  Opis Varchar2(400 ) NOT NULL,
  Nr_marki Integer NOT NULL
)
/

-- Create indexes for table Modele

CREATE INDEX IX_marka_modelu_pojazdu ON Modele (Nr_marki)
/

-- Add keys for table Modele

ALTER TABLE Modele ADD CONSTRAINT PK_Modele PRIMARY KEY (Nr_modelu)
/

ALTER TABLE Modele ADD CONSTRAINT Nr_modelu UNIQUE (Nr_modelu)
/

-- Table Marki

CREATE TABLE Marki(
  Nr_marki Integer NOT NULL,
  Nazwa Varchar2(30 ) NOT NULL,
  Opis Varchar2(300 ) NOT NULL
)
/

-- Add keys for table Marki

ALTER TABLE Marki ADD CONSTRAINT PK_Marki PRIMARY KEY (Nr_marki)
/

ALTER TABLE Marki ADD CONSTRAINT Nr_marki UNIQUE (Nr_marki)
/

-- Table Wlasciciele

CREATE TABLE Wlasciciele(
  Nr_wlasciciela Integer NOT NULL,
  Imie Varchar2(20 ) NOT NULL,
  Nazwisko Varchar2(30 ) NOT NULL,
  Nr_wypozyczalni Integer NOT NULL,
  Nr_adresu Integer NOT NULL
)
/

-- Create indexes for table Wlasciciele

CREATE INDEX IX_wypozyczalnia_wlasciciela ON Wlasciciele (Nr_wypozyczalni)
/

CREATE INDEX IX_adres_wlasciciela ON Wlasciciele (Nr_adresu)
/

-- Add keys for table Wlasciciele

ALTER TABLE Wlasciciele ADD CONSTRAINT PK_Wlasciciele PRIMARY KEY (Nr_wlasciciela)
/

ALTER TABLE Wlasciciele ADD CONSTRAINT Nr_wlasciciela UNIQUE (Nr_wlasciciela)
/

-- Trigger for sequence WypozyczalniaSeq for column Nr_wypozyczalni in table Wypozyczalnie ---------
CREATE OR REPLACE TRIGGER ts_Wypozyczalnie_WypozyczalniaSeq BEFORE INSERT
ON Wypozyczalnie FOR EACH ROW
BEGIN
  :new.Nr_wypozyczalni := WypozyczalniaSeq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Wypozyczalnie_WypozyczalniaSeq AFTER UPDATE OF Nr_wypozyczalni
ON Wypozyczalnie FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_wypozyczalni in table Wypozyczalnie as it uses sequence.');
END;
/

-- Trigger for sequence PracownikSeq for column Nr_pracownika in table Pracownicy ---------
CREATE OR REPLACE TRIGGER ts_Pracownicy_PracownikSeq BEFORE INSERT
ON Pracownicy FOR EACH ROW
BEGIN
  :new.Nr_pracownika := PracownikSeq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Pracownicy_PracownikSeq AFTER UPDATE OF Nr_pracownika
ON Pracownicy FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_pracownika in table Pracownicy as it uses sequence.');
END;
/

-- Trigger for sequence KlientSeq for column Id_klienta in table Klienci ---------
CREATE OR REPLACE TRIGGER ts_Klienci_KlientSeq BEFORE INSERT
ON Klienci FOR EACH ROW
BEGIN
  :new.Id_klienta := KlientSeq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Klienci_KlientSeq AFTER UPDATE OF Id_klienta
ON Klienci FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Id_klienta in table Klienci as it uses sequence.');
END;
/

-- Trigger for sequence PojazdSeq for column Nr_pojazdu in table Pojazdy ---------
CREATE OR REPLACE TRIGGER ts_Pojazdy_PojazdSeq BEFORE INSERT
ON Pojazdy FOR EACH ROW
BEGIN
  :new.Nr_pojazdu := PojazdSeq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Pojazdy_PojazdSeq AFTER UPDATE OF Nr_pojazdu
ON Pojazdy FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_pojazdu in table Pojazdy as it uses sequence.');
END;
/

-- Trigger for sequence StacjaSeq for column Nr_stacji in table Stacje_benzynowe ---------
CREATE OR REPLACE TRIGGER ts_Stacje_benzynowe_StacjaSeq BEFORE INSERT
ON Stacje_benzynowe FOR EACH ROW
BEGIN
  :new.Nr_stacji := StacjaSeq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Stacje_benzynowe_StacjaSeq AFTER UPDATE OF Nr_stacji
ON Stacje_benzynowe FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_stacji in table Stacje_benzynowe as it uses sequence.');
END;
/

-- Trigger for sequence AdresSeq for column Nr_adresu in table Adresy ---------
CREATE OR REPLACE TRIGGER ts_Adresy_AdresSeq BEFORE INSERT
ON Adresy FOR EACH ROW
BEGIN
  :new.Nr_adresu := AdresSeq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Adresy_AdresSeq AFTER UPDATE OF Nr_adresu
ON Adresy FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_adresu in table Adresy as it uses sequence.');
END;
/

-- Trigger for sequence PocztaSeq for column Nr_poczty in table Poczty ---------
CREATE OR REPLACE TRIGGER ts_Poczty_PocztaSeq BEFORE INSERT
ON Poczty FOR EACH ROW
BEGIN
  :new.Nr_poczty := PocztaSeq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Poczty_PocztaSeq AFTER UPDATE OF Nr_poczty
ON Poczty FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_poczty in table Poczty as it uses sequence.');
END;
/

-- Trigger for sequence StanowiskoSeq for column Nr_stanowiska in table Stanowiska ---------
CREATE OR REPLACE TRIGGER ts_Stanowiska_StanowiskoSeq BEFORE INSERT
ON Stanowiska FOR EACH ROW
BEGIN
  :new.Nr_stanowiska := StanowiskoSeq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Stanowiska_StanowiskoSeq AFTER UPDATE OF Nr_stanowiska
ON Stanowiska FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_stanowiska in table Stanowiska as it uses sequence.');
END;
/

-- Trigger for sequence TelefonSeq for column Id_telefonu in table Telefony ---------
CREATE OR REPLACE TRIGGER ts_Telefony_TelefonSeq BEFORE INSERT
ON Telefony FOR EACH ROW
BEGIN
  :new.Id_telefonu := TelefonSeq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Telefony_TelefonSeq AFTER UPDATE OF Id_telefonu
ON Telefony FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Id_telefonu in table Telefony as it uses sequence.');
END;
/

-- Trigger for sequence JezykSeq for column Nr_jezyka in table Jezyki ---------
CREATE OR REPLACE TRIGGER ts_Jezyki_JezykSeq BEFORE INSERT
ON Jezyki FOR EACH ROW
BEGIN
  :new.Nr_jezyka := JezykSeq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Jezyki_JezykSeq AFTER UPDATE OF Nr_jezyka
ON Jezyki FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_jezyka in table Jezyki as it uses sequence.');
END;
/

-- Trigger for sequence ModelSeq for column Nr_modelu in table Modele ---------
CREATE OR REPLACE TRIGGER ts_Modele_ModelSeq BEFORE INSERT
ON Modele FOR EACH ROW
BEGIN
  :new.Nr_modelu := ModelSeq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Modele_ModelSeq AFTER UPDATE OF Nr_modelu
ON Modele FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_modelu in table Modele as it uses sequence.');
END;
/

-- Trigger for sequence ModelSeq for column Nr_marki in table Marki ---------
CREATE OR REPLACE TRIGGER ts_Marki_ModelSeq BEFORE INSERT
ON Marki FOR EACH ROW
BEGIN
  :new.Nr_marki := ModelSeq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Marki_ModelSeq AFTER UPDATE OF Nr_marki
ON Marki FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_marki in table Marki as it uses sequence.');
END;
/

-- Trigger for sequence WlascicielSeq for column Nr_wlasciciela in table Wlasciciele ---------
CREATE OR REPLACE TRIGGER ts_Wlasciciele_WlascicielSeq BEFORE INSERT
ON Wlasciciele FOR EACH ROW
BEGIN
  :new.Nr_wlasciciela := WlascicielSeq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Wlasciciele_WlascicielSeq AFTER UPDATE OF Nr_wlasciciela
ON Wlasciciele FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_wlasciciela in table Wlasciciele as it uses sequence.');
END;
/


-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE Pracownicy ADD CONSTRAINT Zatrudna FOREIGN KEY (Nr_wypozyczalni) REFERENCES Wypozyczalnie (Nr_wypozyczalni)
/



ALTER TABLE Pojazdy ADD CONSTRAINT Wypozyczalnia_posiada_pojazd FOREIGN KEY (Nr_wypozyczalni) REFERENCES Wypozyczalnie (Nr_wypozyczalni)
/



ALTER TABLE Stacje_benzynowe ADD CONSTRAINT Posiada_stacje FOREIGN KEY (Nr_wypozyczalni) REFERENCES Wypozyczalnie (Nr_wypozyczalni)
/



ALTER TABLE Pracownicy ADD CONSTRAINT Pracownik_ma_stanowisko FOREIGN KEY (Nr_stanowiska) REFERENCES Stanowiska (Nr_stanowiska)
/



ALTER TABLE Wypozyczalnie ADD CONSTRAINT Ma_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Pracownicy ADD CONSTRAINT Pracownik_posiada_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Klienci ADD CONSTRAINT Klient_posiada_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Wypozyczenia ADD CONSTRAINT Klient_wypozycza FOREIGN KEY (Id_klienta) REFERENCES Klienci (Id_klienta)
/



ALTER TABLE Wypozyczenia ADD CONSTRAINT Pojazd_jest_wypozyczony FOREIGN KEY (Nr_pojazdu) REFERENCES Pojazdy (Nr_pojazdu)
/



ALTER TABLE Telefony ADD CONSTRAINT Pracownik_posiada_telefon FOREIGN KEY (Nr_pracownika) REFERENCES Pracownicy (Nr_pracownika)
/



ALTER TABLE Telefony ADD CONSTRAINT Klient_posiada_telefon FOREIGN KEY (Id_klienta) REFERENCES Klienci (Id_klienta)
/



ALTER TABLE Adresy ADD CONSTRAINT Adres_na_poczte FOREIGN KEY (Nr_poczty) REFERENCES Poczty (Nr_poczty)
/



ALTER TABLE Znajomosc_jezykow ADD CONSTRAINT Jezyk_jest_znany FOREIGN KEY (Nr_jezyka) REFERENCES Jezyki (Nr_jezyka)
/



ALTER TABLE Znajomosc_jezykow ADD CONSTRAINT Sprzedawca_zna_jezyki FOREIGN KEY (Nr_pracownika) REFERENCES Sprzedawcy (Nr_pracownika)
/



ALTER TABLE Modele ADD CONSTRAINT Marka_ma_model FOREIGN KEY (Nr_marki) REFERENCES Marki (Nr_marki)
/



ALTER TABLE Pojazdy ADD CONSTRAINT Pojazd_ma_model FOREIGN KEY (Nr_modelu) REFERENCES Modele (Nr_modelu)
/



ALTER TABLE Wlasciciele ADD CONSTRAINT Wypozyczalnia_ma_wlasciciela FOREIGN KEY (Nr_wypozyczalni) REFERENCES Wypozyczalnie (Nr_wypozyczalni)
/



ALTER TABLE Wlasciciele ADD CONSTRAINT Wlasciciel_ma_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/





