//package com.sanya1am.consecutivepractices.listWithDetails.data.mock
//
//import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieFullEntity
//import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieShortEntity
//import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieType
//
//object MoviesData {
//    val moviesShort = listOf(
//        MovieShortEntity(
//            id = "6652110",
//            name = "Любовь в большом городе",
//            year = "2024",
//            type = MovieType.MOVIE,
//            rating = MovieFullEntity.RatingEntity(
//                kp = "8.577",
//                imdb = "8.9",
//                filmCritics = "0.0",
//                russianFilmCritics = "0.0",
//                await = null
//            ),
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10703859/d5affc5f-c637-4dc5-8c81-ed1256f3ae1b/x1000"
//        ),
//        MovieShortEntity(
//            id = "5416975",
//            name = "Атака Титанов: Финал. Расширенная версия",
//            year = "2023",
//            type = MovieType.MOVIE,
//            rating = MovieFullEntity.RatingEntity(
//                kp = "9.092",
//                imdb = "9.2",
//                filmCritics = "0.0",
//                russianFilmCritics = "0.0",
//                await = null
//            ),
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10812607/ba203cdf-3886-4b50-a73a-563bc177fe50/x1000"
//        ),
//        MovieShortEntity(
//            id = "5457394",
//            name = "Хватай Сон-джэ и беги",
//            year = "2024",
//            type = MovieType.MOVIE,
//            rating = MovieFullEntity.RatingEntity(
//                kp = "8.839",
//                imdb = "8.6",
//                filmCritics = "0.0",
//                russianFilmCritics = "0.0",
//                await = null
//            ),
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10900341/a19d7044-05df-4900-95fb-5ba252ea8f16/x1000"
//        ),
//        MovieShortEntity(
//            id = "5404281",
//            name = "BBC: Планета Земля III",
//            year = "2023",
//            type = MovieType.TV_SERIES,
//            rating = MovieFullEntity.RatingEntity(
//                kp = "9.0",
//                imdb = "9.2",
//                filmCritics = "0.0",
//                russianFilmCritics = "0.0",
//                await = null
//            ),
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10835644/fb984c11-3b4b-45d8-a449-60d2f94012e2/x1000"
//        ),
//        MovieShortEntity(
//            id = "5396827",
//            name = "Лечу тебе навстречу",
//            year = "2023",
//            type = MovieType.TV_SERIES,
//            rating = MovieFullEntity.RatingEntity(
//                kp = "8.545",
//                imdb = "8.5",
//                filmCritics = "0.0",
//                russianFilmCritics = "0.0",
//                await = null
//            ),
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/9784475/e84f5a2e-1b4f-420e-9c2a-6ada45c90163/x1000"
//        ),
//        MovieShortEntity(
//            id = "5377063",
//            name = "Скрытая любовь",
//            year = "2023",
//            type = MovieType.TV_SERIES,
//            rating = MovieFullEntity.RatingEntity(
//                kp = "8.769",
//                imdb = "8.6",
//                filmCritics = "0.0",
//                russianFilmCritics = "0.0",
//                await = null
//            ),
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10592371/725b7054-195b-4fb2-af6e-67ea84cef51b/x1000"
//        ),
//        MovieShortEntity(
//            id = "5365825",
//            name = "Юн Джон-нён: Звезда родилась",
//            year = "2024",
//            type = MovieType.TV_SERIES,
//            rating = MovieFullEntity.RatingEntity(
//                kp = "8.87",
//                imdb = "8.5",
//                filmCritics = "0.0",
//                russianFilmCritics = "0.0",
//                await = null
//            ),
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10671298/1553aae2-8cfb-4b7d-9268-a0578d35abba/x1000"
//        ),
//        MovieShortEntity(
//            id = "5276678",
//            name = "Мерцающий арбуз",
//            year = "2023",
//            type = MovieType.TV_SERIES,
//            rating = MovieFullEntity.RatingEntity(
//                kp = "8.991",
//                imdb = "8.9",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = null
//            ),
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10703959/ad987cc5-8f92-4387-b949-dfbb3a6a06ae/x1000"
//        ),
//        MovieShortEntity(
//            id = "5245026",
//            name = "Влюблённые",
//            year = "2023",
//            type = MovieType.TV_SERIES,
//            rating = MovieFullEntity.RatingEntity(
//                kp = "8.692",
//                imdb = "8.9",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = null
//            ),
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10671298/7ffe1945-89b1-4ef8-8ef0-3deec8027435/x1000"
//        )
//    )
//
//    val moviesFull = listOf(
//        MovieFullEntity(
//            id = "6652110",
//            name = "Любовь в большом городе",
//            alternativeName = null,
//            type = MovieType.MOVIE,
//            year = "2024",
//            description = "История о любви и жизни в большом городе, где два человека начинают отношения после долгого времени знакомства.",
//            shortDescription = null,
//            status = "completed",
//            rating = MovieFullEntity.RatingEntity(
//                kp = "8.577",
//                imdb = "8.9",
//                filmCritics = "0.0",
//                russianFilmCritics = "0.0",
//                await = null
//            ),
//            votes = MovieFullEntity.VotesEntity(
//                kp = "18854",
//                imdb = "10170",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = "0"
//            ),
//            movieLength = "120",
//            totalSeriesLength = null,
//            seriesLength = null,
//            ratingMpaa = "PG-13",
//            ageRating = "12+",
//            posterUrl = "https://image.openmoviedb.com/kinopoisk-images/10703859/d5affc5f-c637-4dc5-8c81-ed1256f3ae1b/orig",
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10703859/d5affc5f-c637-4dc5-8c81-ed1256f3ae1b/x1000",
//            genres = listOf("драма", "мелодрама"),
//            countries = listOf("Россия"),
//            releaseYears = "2024",
//            isSeries = false,
//            ticketsOnSale = false
//        ),
//        MovieFullEntity(
//            id = "5416975",
//            name = "Атака Титанов: Финал. Расширенная версия",
//            alternativeName = null,
//            type = MovieType.MOVIE,
//            year = "2023",
//            description = "Конец захватывающей саги, где человечество борется за выживание в условиях жестоких титанов.",
//            shortDescription = null,
//            status = "completed",
//            rating = MovieFullEntity.RatingEntity(
//                kp = "9.092",
//                imdb = "9.2",
//                filmCritics = "0.0",
//                russianFilmCritics = "0.0",
//                await = null
//            ),
//            votes = MovieFullEntity.VotesEntity(
//                kp = "18854",
//                imdb = "10170",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = "0"
//            ),
//            movieLength = "150",
//            totalSeriesLength = null,
//            seriesLength = null,
//            ratingMpaa = "R",
//            ageRating = "16+",
//            posterUrl = "https://image.openmoviedb.com/kinopoisk-images/10812607/ba203cdf-3886-4b50-a73a-563bc177fe50/orig",
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10812607/ba203cdf-3886-4b50-a73a-563bc177fe50/x1000",
//            genres = listOf("аниме", "фэнтези"),
//            countries = listOf("Япония"),
//            releaseYears = "2023",
//            isSeries = false,
//            ticketsOnSale = true
//        ),
//        MovieFullEntity(
//            id = "5457394",
//            name = "Хватай Сон-джэ и беги",
//            alternativeName = "Seonjae eopgo twieo",
//            type = MovieType.MOVIE,
//            year = "2024",
//            description = "Фанатка популярного актёра Рю Сон-джэ узнаёт, что её кумир покончил с собой, и невероятным образом переносится на 15 лет назад.",
//            shortDescription = null,
//            status = "completed",
//            rating = MovieFullEntity.RatingEntity(
//                kp = "8.839",
//                imdb = "8.6",
//                filmCritics = "0.0",
//                russianFilmCritics = "0.0",
//                await = null
//            ),
//            votes = MovieFullEntity.VotesEntity(
//                kp = "18854",
//                imdb = "10170",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = "0"
//            ),
//            movieLength = "125",
//            totalSeriesLength = null,
//            seriesLength = null,
//            ratingMpaa = "PG-13",
//            ageRating = "12+",
//            posterUrl = "https://image.openmoviedb.com/kinopoisk-images/10900341/a19d7044-05df-4900-95fb-5ba252ea8f16/orig",
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10900341/a19d7044-05df-4900-95fb-5ba252ea8f16/x1000",
//            genres = listOf("мелодрама", "комедия", "фэнтези"),
//            countries = listOf("Южная Корея"),
//            releaseYears = "2024",
//            isSeries = true,
//            ticketsOnSale = false
//        ),
//        MovieFullEntity(
//            id = "5404281",
//            name = "BBC: Планета Земля III",
//            alternativeName = "Planet Earth III",
//            type = MovieType.TV_SERIES,
//            year = "2023",
//            description = "Новые пейзажи, невероятные открытия моделей поведения животных и возможность наблюдать за напряжённой борьбой удивительных обитателей нашей планеты.",
//            shortDescription = null,
//            status = "completed",
//            rating = MovieFullEntity.RatingEntity(
//                kp = "9",
//                imdb = "9.2",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = null
//            ),
//            votes = MovieFullEntity.VotesEntity(
//                kp = "281",
//                imdb = "4002",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = "0"
//            ),
//            movieLength = null,
//            totalSeriesLength = "58",
//            seriesLength = null,
//            ratingMpaa = null,
//            ageRating = null,
//            posterUrl = "https://image.openmoviedb.com/kinopoisk-images/10835644/fb984c11-3b4b-45d8-a449-60d2f94012e2/orig",
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10835644/fb984c11-3b4b-45d8-a449-60d2f94012e2/x1000",
//            genres = listOf("документальный"),
//            countries = listOf("Великобритания"),
//            releaseYears = "2023",
//            isSeries = true,
//            ticketsOnSale = false
//        ),
//        MovieFullEntity(
//            id = "5396827",
//            name = "Лечу тебе навстречу",
//            alternativeName = "Dang wo fei ben xiang ni",
//            type = MovieType.TV_SERIES,
//            year = "2023",
//            description = null,
//            shortDescription = null,
//            status = "completed",
//            rating = MovieFullEntity.RatingEntity(
//                kp = "8.545",
//                imdb = "8.5",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = null
//            ),
//            votes = MovieFullEntity.VotesEntity(
//                kp = "4682",
//                imdb = "2041",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = "0"
//            ),
//            movieLength = null,
//            totalSeriesLength = null,
//            seriesLength = "35",
//            ratingMpaa = null,
//            ageRating = null,
//            posterUrl = "https://image.openmoviedb.com/kinopoisk-images/9784475/e84f5a2e-1b4f-420e-9c2a-6ada45c90163/orig",
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/9784475/e84f5a2e-1b4f-420e-9c2a-6ada45c90163/x1000",
//            genres = listOf("мелодрама", "комедия"),
//            countries = listOf("Китай"),
//            releaseYears = "2023",
//            isSeries = true,
//            ticketsOnSale = false
//        ),
//        MovieFullEntity(
//            id = "5377063",
//            name = "Скрытая любовь",
//            alternativeName = "Tou tou cang bu zhu",
//            type = MovieType.TV_SERIES,
//            year = "2023",
//            description = null,
//            shortDescription = null,
//            status = "completed",
//            rating = MovieFullEntity.RatingEntity(
//                kp = "8.769",
//                imdb = "8.6",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = null
//            ),
//            votes = MovieFullEntity.VotesEntity(
//                kp = "13732",
//                imdb = "6430",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = "0"
//            ),
//            movieLength = null,
//            totalSeriesLength = null,
//            seriesLength = "45",
//            ratingMpaa = null,
//            ageRating = null,
//            posterUrl = "https://image.openmoviedb.com/kinopoisk-images/10592371/725b7054-195b-4fb2-af6e-67ea84cef51b/orig",
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10592371/725b7054-195b-4fb2-af6e-67ea84cef51b/x1000",
//            genres = listOf("драма", "мелодрама"),
//            countries = listOf("Китай"),
//            releaseYears = "2023",
//            isSeries = true,
//            ticketsOnSale = false,
//        ),
//        MovieFullEntity(
//            id = "5365825",
//            name = "Юн Джон-нён: Звезда родилась",
//            alternativeName = "Jeongnyeoni",
//            type = MovieType.TV_SERIES,
//            year = "2024",
//            description = "1956 год, послевоенная Корея. Простая девушка из города Мокпхо так впечатляется выступлением столичного музыкального театра, что решает стать артисткой сцены. Она тайком пробирается в багажный отдел и прибывает вместе с театром в Сеул.",
//            shortDescription = null,
//            status = "completed",
//            rating = MovieFullEntity.RatingEntity(
//                kp = "8.87",
//                imdb = "8.5",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = null
//            ),
//            votes = MovieFullEntity.VotesEntity(
//                kp = "531",
//                imdb = "1054",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = "0"
//            ),
//            movieLength = null,
//            totalSeriesLength = null,
//            seriesLength = "70",
//            ratingMpaa = null,
//            ageRating = null,
//            posterUrl = "https://image.openmoviedb.com/kinopoisk-images/10671298/1553aae2-8cfb-4b7d-9268-a0578d35abba/orig",
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10671298/1553aae2-8cfb-4b7d-9268-a0578d35abba/x1000",
//            genres = listOf("драма", "музыка"),
//            countries = listOf("Корея Южная"),
//            releaseYears = "2024",
//            isSeries = true,
//            ticketsOnSale = false
//        ),
//        MovieFullEntity(
//            id = "5276678",
//            name = "Мерцающий арбуз",
//            alternativeName = "Banjjakineun witeomellon",
//            type = MovieType.TV_SERIES,
//            year = "2023",
//            description = "Старшеклассник, страстно обожающий музыку и играющий на гитаре в рок-группе, однажды заходит в загадочный магазин музыкальных инструментов и переносится из 2023 года в 1995-й.",
//            shortDescription = null,
//            status = "completed",
//            rating = MovieFullEntity.RatingEntity(
//                kp = "8.991",
//                imdb = "8.9",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = null
//            ),
//            votes = MovieFullEntity.VotesEntity(
//                kp = "12027",
//                imdb = "8038",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = "0"
//            ),
//            movieLength = null,
//            totalSeriesLength = null,
//            seriesLength = "70",
//            ratingMpaa = null,
//            ageRating = null,
//            posterUrl = "https://image.openmoviedb.com/kinopoisk-images/10703959/ad987cc5-8f92-4387-b949-dfbb3a6a06ae/orig",
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10703959/ad987cc5-8f92-4387-b949-dfbb3a6a06ae/x1000",
//            genres = listOf("мелодрама", "музыка", "фэнтези"),
//            countries = listOf("Корея Южная"),
//            releaseYears = "2023",
//            isSeries = true,
//            ticketsOnSale = false
//        ),
//        MovieFullEntity(
//            id = "5245026",
//            name = "Влюблённые",
//            alternativeName = "Yeonin",
//            type = MovieType.TV_SERIES,
//            year = "2023",
//            description = "Маньчжуро-корейская война. Ю Гиль-чхэ принадлежит к знатному роду, и семья подыскивает ей жениха. Вскоре в её жизнь входит загадочный незнакомец Ли Джан-хён, который внезапно появляется в высших кругах. Несмотря на политические распри и интриги, молодые влюбляются друг в друга.",
//            shortDescription = "Любовный многоугольник на фоне исторических потрясений в империи Чосон. «Унесённые ветром» на корейский лад",
//            status = "completed",
//            rating = MovieFullEntity.RatingEntity(
//                kp = "8.692",
//                imdb = "8.9",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = null
//            ),
//            votes = MovieFullEntity.VotesEntity(
//                kp = "13784",
//                imdb = "2938",
//                filmCritics = "0",
//                russianFilmCritics = "0",
//                await = "0"
//            ),
//            movieLength = null,
//            totalSeriesLength = null,
//            seriesLength = "60",
//            ratingMpaa = null,
//            ageRating = null,
//            posterUrl = "https://image.openmoviedb.com/kinopoisk-images/10671298/7ffe1945-89b1-4ef8-8ef0-3deec8027435/orig",
//            posterPreviewUrl = "https://image.openmoviedb.com/kinopoisk-images/10671298/7ffe1945-89b1-4ef8-8ef0-3deec8027435/x1000",
//            genres = listOf("мелодрама", "драма", "история"),
//            countries = listOf("Корея Южная"),
//            releaseYears = "2023",
//            isSeries = true,
//            ticketsOnSale = false
//        )
//
//    )
//}