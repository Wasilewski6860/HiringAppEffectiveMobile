package ru.hiringapp.base_feature.activity_provider

/**
 * Маркерный интерфейс для фрагментов, не являющихся экраном.
 *
 * Для чего нужен этот интерфейс? В message controller есть логика выбора контейнера,
 * в котором будем показывать снеки. Там берется последний открытый фрагмент.
 * Так вот, бывают случаи, когда такие фрагменты не нужно учитывать.
 * Например, есть боттомшит авторизации с viewpager внутри. Там нужны снеки в рамках боттомшита,
 * а не внутри вьюпеджера, поэтому фрагменты viewpager помечаем данным фрагментом
 */
interface NotScreen