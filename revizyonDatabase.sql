PGDMP  /         
    	        |         
   newTourism    16.3    16.3 5    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    25064 
   newTourism    DATABASE     �   CREATE DATABASE "newTourism" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "newTourism";
                postgres    false            �            1259    25066    facility    TABLE     d   CREATE TABLE public.facility (
    facility_name text NOT NULL,
    facility_id integer NOT NULL
);
    DROP TABLE public.facility;
       public         heap    postgres    false            �            1259    25182    facility_facility_id_seq    SEQUENCE     �   CREATE SEQUENCE public.facility_facility_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.facility_facility_id_seq;
       public          postgres    false    215            �           0    0    facility_facility_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.facility_facility_id_seq OWNED BY public.facility.facility_id;
          public          postgres    false    225            �            1259    25075    hostel    TABLE     x   CREATE TABLE public.hostel (
    hostel_name text NOT NULL,
    hostel_id integer NOT NULL,
    hostel_price integer
);
    DROP TABLE public.hostel;
       public         heap    postgres    false            �            1259    25192    hostel_hostel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hostel_hostel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.hostel_hostel_id_seq;
       public          postgres    false    216            �           0    0    hostel_hostel_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.hostel_hostel_id_seq OWNED BY public.hostel.hostel_id;
          public          postgres    false    226            �            1259    25100    hotel    TABLE     �  CREATE TABLE public.hotel (
    hotel_name text NOT NULL,
    hotel_address text NOT NULL,
    hotel_mail text NOT NULL,
    hotel_pno bigint NOT NULL,
    hotel_star integer NOT NULL,
    hotel_hostel_type_id integer NOT NULL,
    hotel_id integer NOT NULL,
    hotel_room_service boolean,
    hotel_spa boolean,
    hotel_concierge boolean,
    hotel_pool boolean,
    hotel_wifi boolean,
    hotel_park boolean,
    hotel_fitness boolean,
    hotel_city text
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    25223    hotel_hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hotel_hotel_id_seq;
       public          postgres    false    218            �           0    0    hotel_hotel_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.hotel_hotel_id_seq OWNED BY public.hotel.hotel_id;
          public          postgres    false    228            �            1259    25109    reservation    TABLE     �  CREATE TABLE public.reservation (
    reservation_customer_name text NOT NULL,
    reservation_hotel_id integer NOT NULL,
    reservation_strt_date date NOT NULL,
    reservation_end_date date NOT NULL,
    reservation_adult_number integer NOT NULL,
    reservation_children_number integer NOT NULL,
    reservation_total integer,
    reservation_id integer NOT NULL,
    reservation_room_id integer NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    25152    reservation_reservation_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reservation_reservation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.reservation_reservation_id_seq;
       public          postgres    false    219            �           0    0    reservation_reservation_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.reservation_reservation_id_seq OWNED BY public.reservation.reservation_id;
          public          postgres    false    224            �            1259    25091    room    TABLE     �   CREATE TABLE public.room (
    room_name text NOT NULL,
    room_price integer NOT NULL,
    room_stock integer NOT NULL,
    room_id integer NOT NULL,
    room_hotel_id integer NOT NULL,
    room_season_id integer
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    25202    room_room_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.room_room_id_seq;
       public          postgres    false    217            �           0    0    room_room_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.room_room_id_seq OWNED BY public.room.room_id;
          public          postgres    false    227            �            1259    25117    season    TABLE     �   CREATE TABLE public.season (
    season_name text NOT NULL,
    season_strt_date date NOT NULL,
    season_end_date date NOT NULL,
    season_fare numeric,
    season_id integer NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    25141    season_season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.season_season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.season_season_id_seq;
       public          postgres    false    220            �           0    0    season_season_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.season_season_id_seq OWNED BY public.season.season_id;
          public          postgres    false    223            �            1259    25124    user    TABLE     �   CREATE TABLE public."user" (
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_role text NOT NULL,
    user_full_name text NOT NULL,
    user_id integer NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    25131    user_user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.user_user_id_seq;
       public          postgres    false    221            �           0    0    user_user_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.user_user_id_seq OWNED BY public."user".user_id;
          public          postgres    false    222            8           2604    25183    facility facility_id    DEFAULT     |   ALTER TABLE ONLY public.facility ALTER COLUMN facility_id SET DEFAULT nextval('public.facility_facility_id_seq'::regclass);
 C   ALTER TABLE public.facility ALTER COLUMN facility_id DROP DEFAULT;
       public          postgres    false    225    215            9           2604    25193    hostel hostel_id    DEFAULT     t   ALTER TABLE ONLY public.hostel ALTER COLUMN hostel_id SET DEFAULT nextval('public.hostel_hostel_id_seq'::regclass);
 ?   ALTER TABLE public.hostel ALTER COLUMN hostel_id DROP DEFAULT;
       public          postgres    false    226    216            ;           2604    25224    hotel hotel_id    DEFAULT     p   ALTER TABLE ONLY public.hotel ALTER COLUMN hotel_id SET DEFAULT nextval('public.hotel_hotel_id_seq'::regclass);
 =   ALTER TABLE public.hotel ALTER COLUMN hotel_id DROP DEFAULT;
       public          postgres    false    228    218            <           2604    25153    reservation reservation_id    DEFAULT     �   ALTER TABLE ONLY public.reservation ALTER COLUMN reservation_id SET DEFAULT nextval('public.reservation_reservation_id_seq'::regclass);
 I   ALTER TABLE public.reservation ALTER COLUMN reservation_id DROP DEFAULT;
       public          postgres    false    224    219            :           2604    25203    room room_id    DEFAULT     l   ALTER TABLE ONLY public.room ALTER COLUMN room_id SET DEFAULT nextval('public.room_room_id_seq'::regclass);
 ;   ALTER TABLE public.room ALTER COLUMN room_id DROP DEFAULT;
       public          postgres    false    227    217            =           2604    25142    season season_id    DEFAULT     t   ALTER TABLE ONLY public.season ALTER COLUMN season_id SET DEFAULT nextval('public.season_season_id_seq'::regclass);
 ?   ALTER TABLE public.season ALTER COLUMN season_id DROP DEFAULT;
       public          postgres    false    223    220            >           2604    25132    user user_id    DEFAULT     n   ALTER TABLE ONLY public."user" ALTER COLUMN user_id SET DEFAULT nextval('public.user_user_id_seq'::regclass);
 =   ALTER TABLE public."user" ALTER COLUMN user_id DROP DEFAULT;
       public          postgres    false    222    221            �          0    25066    facility 
   TABLE DATA           >   COPY public.facility (facility_name, facility_id) FROM stdin;
    public          postgres    false    215   �<       �          0    25075    hostel 
   TABLE DATA           F   COPY public.hostel (hostel_name, hostel_id, hostel_price) FROM stdin;
    public          postgres    false    216   :=       �          0    25100    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_name, hotel_address, hotel_mail, hotel_pno, hotel_star, hotel_hostel_type_id, hotel_id, hotel_room_service, hotel_spa, hotel_concierge, hotel_pool, hotel_wifi, hotel_park, hotel_fitness, hotel_city) FROM stdin;
    public          postgres    false    218   �=       �          0    25109    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_customer_name, reservation_hotel_id, reservation_strt_date, reservation_end_date, reservation_adult_number, reservation_children_number, reservation_total, reservation_id, reservation_room_id) FROM stdin;
    public          postgres    false    219   N?       �          0    25091    room 
   TABLE DATA           i   COPY public.room (room_name, room_price, room_stock, room_id, room_hotel_id, room_season_id) FROM stdin;
    public          postgres    false    217   @       �          0    25117    season 
   TABLE DATA           h   COPY public.season (season_name, season_strt_date, season_end_date, season_fare, season_id) FROM stdin;
    public          postgres    false    220   �@       �          0    25124    user 
   TABLE DATA           ^   COPY public."user" (user_name, user_password, user_role, user_full_name, user_id) FROM stdin;
    public          postgres    false    221   �@       �           0    0    facility_facility_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.facility_facility_id_seq', 9, true);
          public          postgres    false    225            �           0    0    hostel_hostel_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.hostel_hostel_id_seq', 10, true);
          public          postgres    false    226            �           0    0    hotel_hotel_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 16, true);
          public          postgres    false    228            �           0    0    reservation_reservation_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 51, true);
          public          postgres    false    224            �           0    0    room_room_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.room_room_id_seq', 14, true);
          public          postgres    false    227            �           0    0    season_season_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.season_season_id_seq', 2, true);
          public          postgres    false    223            �           0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 8, true);
          public          postgres    false    222            @           2606    25185    facility facility_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.facility
    ADD CONSTRAINT facility_pkey PRIMARY KEY (facility_id);
 @   ALTER TABLE ONLY public.facility DROP CONSTRAINT facility_pkey;
       public            postgres    false    215            B           2606    25195    hostel hostel_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.hostel
    ADD CONSTRAINT hostel_pkey PRIMARY KEY (hostel_id);
 <   ALTER TABLE ONLY public.hostel DROP CONSTRAINT hostel_pkey;
       public            postgres    false    216            F           2606    25226    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    218            H           2606    25155    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    219            D           2606    25205    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    217            J           2606    25144    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    220            L           2606    25134    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    221            �   v   x�.H�4���/I�Qp��K�L-JO�4�<��*7U�#�����ӄ���Ԓ��*��L]�LNS$����ĢlN3.s}#��D��Ԣ���LNC.�̒���b�Լ��"Ns�=... ��'      �   �   x�]�1
�@F���S�	d71�E
j��B��	�����Z��{�Rl�o�M�\H��o
��	���A*wq\J�)&)%�Õ���|ߵ�#7t��`��]%���!���P�����^��NÓ� ��,��q���W1�      �   d  x�m��n�0Fg�)� (R�D�:�S���V��%1Ŋc#����c0��҉-�zJI�ʶ<X�=�N}P���9�dT��}uJ�ɀNR�������B�`�c��5��#C]�!7�	Ð1�'Ϣ�rR�9�y!����I!rN_@B��=τ�b��>�ҥ^��e��X@�m��jmkp����7�@�X���7��V_jå�С0�����m�)�����瑸�w�_�������k�G�H5s����"u�%d�N����)a�t���Vվ��|��k!���8q�F�U�HǀE��aZ���W�'��l������ⵗ�Y�P#��u/�+-�"���7�m�S8���8�7����      �   �   x�M��
�0F盧�Dn��fT��ut�p���"�i;�w����b*w9�w8wM�:
�X
�a%���Y��� ��_|�[����� ��hf�IB(��`*0���y��L=�l�9�&K��Φ���:��>��B(�Q�e*9k*���V�q�����?G�SG�RY�)�v��t:n      �   �   x�M�1�0��>EO�b'�vgBL��PUEJT��c�Ab���ߗ���)�Fc���ϯR�a���$��-��΢����\���/�/Z�͒�qv:�5v+[�{�z����$��j�ㄈ_(�.P      �   A   x�;�!'[!2����\N##]C �2Mu�8�L8��l���K�DUjUj
ӥ�\1z\\\ o�Q      �   �   x�EN��0��M�	�� }
hP:J�'�Vl'�B�	#%uz:�����~��t\{�M^\e{>�]LϮ�Gw��Z�xAa4�w��"(�^UI����u�9�t����#cK�,2�;Ϊ�<�yJ���k|y�Sf��m�s��VD�w@     