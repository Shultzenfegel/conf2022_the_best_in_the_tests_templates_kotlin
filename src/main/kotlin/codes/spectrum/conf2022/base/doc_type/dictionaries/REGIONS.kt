package codes.spectrum.conf2022.base.doc_type.dictionaries

object REGIONS : BaseDictionary {
    private var _dict: Map<String, String>? = null

    override val dict: Map<String, String>
        get() {
            return if (_dict != null) _dict!!
            else {
                _dict = getDictionary()
                _dict!!
            }
        }

    override val codesAndValue = """
01	Республика Адыгея (Адыгея)
02	Республика Башкортостан
03	Республика Бурятия
04	Республика Алтай
05	Республика Дагестан
06	Республика Ингушетия
07	Кабардино-Балкарская Республика
08	Республика Калмыкия
09	Карачаево-Черкесская Республика
10	Республика Карелия
11	Республика Коми
12	Республика Марий Эл
13	Республика Мордовия
14	Республика Саха (Якутия)
15	Республика Северная Осетия - Алания
16	Республика Татарстан (Татарстан)
17	Республика Тыва
18	Удмуртская Республика
19	Республика Хакасия
20	Чеченская Республика
21	Чувашская Республика - Чувашия
22	Алтайский край
23	Краснодарский край
24	Красноярский край
25	Приморский край
26	Ставропольский край
27	Хабаровский край
28	Амурская область
29	Архангельская область
30	Астраханская область
31	Белгородская область
32	Брянская область
33	Владимирская область
34	Волгоградская область
35	Вологодская область
36	Воронежская область
37	Ивановская область
38	Иркутская область
39	Калининградская область
40	Калужская область
41	Камчатский край
42	Кемеровская область
43	Кировская область
44	Костромская область
45	Курганская область
46	Курская область
47	Ленинградская область
48	Липецкая область
49	Магаданская область
50	Московская область
51	Мурманская область
52	Нижегородская область
53	Новгородская область
54	Новосибирская область
55	Омская область
56	Оренбургская область
57	Орловская область
58	Пензенская область
59	Пермский край
60	Псковская область
61	Ростовская область
62	Рязанская область
63	Самарская область
64	Саратовская область
65	Сахалинская область
66	Свердловская область
67	Смоленская область
68	Тамбовская область
69	Тверская область
70	Томская область
71	Тульская область
72	Тюменская область
73	Ульяновская область
74	Челябинская область
75	Забайкальский край
76	Ярославская область
77	г. Москва
78	Санкт-Петербург
79	Еврейская автономная область
83	Ненецкий автономный округ
86	Ханты-Мансийский автономный округ - Югра
87	Чукотский автономный округ
89	Ямало-Ненецкий автономный округ
99	Иные территории, включая город и космодром Байконур
    """.trimIndent()
}