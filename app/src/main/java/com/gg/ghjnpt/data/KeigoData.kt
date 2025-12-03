package com.gg.ghjnpt.data

object KeigoData {
    val keigo1 = listOf(
        // 1. 하다 (스루)
        KeigoExpression("する", "하다", "する", "스루", "기본형", baseFormId = 1),
        KeigoExpression("いたします", "하겠습니다", "いたします", "이타시마스", "겸양어 (자신)", baseFormId = 1),
        KeigoExpression("なさいます", "하십니다", "なさいます", "나사이마스", "존경어 (상대)", baseFormId = 1),
        KeigoExpression("させていただきます", "허락받아 하겠습니다", "させていただきます", "사세테 이타다키마스", "수혜·허락형", baseFormId = 1),

        // 2. 가다 (이쿠)
        KeigoExpression("行く", "가다", "いく", "이쿠", "기본형", baseFormId = 2),
        KeigoExpression("参ります", "가겠습니다", "まいります", "마이리마스", "겸양어 (자신)", baseFormId = 2),
        KeigoExpression("いらっしゃいます", "가십니다", "いらっしゃいます", "이랏샤이마스", "존경어 (상대)", baseFormId = 2),
        KeigoExpression("行かせていただきます", "가도록 하겠습니다", "いかせていただきます", "이카세테 이타다키마스", "수혜·허락형", baseFormId = 2),

        // 3. 오다 (쿠루)
        KeigoExpression("来る", "오다", "くる", "쿠루", "기본형", baseFormId = 3),
        KeigoExpression("参ります", "오겠습니다", "まいります", "마이리마스", "겸양어 (자신)", baseFormId = 3),
        KeigoExpression("いらっしゃいます", "오십니다", "いらっしゃいます", "이랏샤이마스", "존경어 (상대)", baseFormId = 3),
        KeigoExpression("来させていただきます", "오도록 하겠습니다", "こさせていただく", "코사세테 이타다키마스", "수혜·허락형", baseFormId = 3),

        // 4. 있다 (이루)
        KeigoExpression("いる", "있다", "いる", "이루", "기본형", baseFormId = 4),
        KeigoExpression("おります", "있습니다", "おります", "오리마스", "겸양어 (자신)", baseFormId = 4),
        KeigoExpression("いらっしゃいます", "계십니다", "いらっしゃいます", "이랏샤이마스", "존경어 (상대)", baseFormId = 4),

        // 5. 말하다 (이우)
        KeigoExpression("言う", "말하다", "いう", "이우", "기본형", baseFormId = 5),
        KeigoExpression("申し上げます", "말씀드립니다", "もうしあげます", "모우시아게마스", "겸양어 (자신)", baseFormId = 5),
        KeigoExpression("おっしゃいます", "말씀하십니다", "おっしゃいます", "옷샤이마스", "존경어 (상대)", baseFormId = 5),

        // 6. 묻다/듣다 (키쿠)
        KeigoExpression("聞く", "묻다/듣다", "きく", "키쿠", "기본형", baseFormId = 6),
        KeigoExpression("伺います", "여쭙겠습니다", "うかがいます", "우카가이마스", "겸양어 (자신)", baseFormId = 6),
        KeigoExpression("お聞きになります", "들으십니다", "おききになります", "오키키니 나리마스", "존경어 (상대)", baseFormId = 6),
        KeigoExpression("聞かせていただきます", "듣도록 하겠습니다", "きかせていただきます", "키카세테 이타다키마스", "수혜·허락형", baseFormId = 6),

        // 7. 보다 (미루)
        KeigoExpression("見る", "보다", "みる", "미루", "기본형", baseFormId = 7),
        KeigoExpression("拝見します", "보겠습니다 (뵙겠습니다)", "はいけんします", "하이켄 시마스", "겸양어 (자신)", baseFormId = 7),
        KeigoExpression("ご覧になります", "보십니다", "ごらんになります", "고란니 나리마스", "존경어 (상대)", baseFormId = 7),

        // 8. 먹다 (타베루)
        KeigoExpression("食べる", "먹다", "たべる", "타베루", "기본형", baseFormId = 8),
        KeigoExpression("いただきます", "먹겠습니다", "いただきます", "이타다키마스", "겸양어 (자신)", baseFormId = 8),
        KeigoExpression("召し上がります", "드십니다", "めしあがります", "메시아가리마스", "존경어 (상대)", baseFormId = 8),

        // 9. 마시다 (노무) - 먹다와 존경/겸양이 같음
        KeigoExpression("飲む", "마시다", "のむ", "노무", "기본형", baseFormId = 9),
        KeigoExpression("いただきます", "마시겠습니다", "いただきます", "이타다키마스", "겸양어 (자신)", baseFormId = 9),
        KeigoExpression("召し上がります", "드십니다", "めしあがります", "메시아가리마스", "존경어 (상대)", baseFormId = 9),

        // 10. 주다 (아게루)
        KeigoExpression("あげる", "주다 (내가 남에게)", "あげる", "아게루", "기본형", baseFormId = 10),
        KeigoExpression("差し上げます", "드리겠습니다", "さしあげます", "사시아게마스", "겸양어 (자신)", baseFormId = 10),

        // 11. 받다 (모우라우) - 장음 주의
        KeigoExpression("もらう", "받다", "もらう", "모우라우", "기본형", baseFormId = 11),
        KeigoExpression("いただきます", "받습니다", "いただきます", "이타다키마스", "겸양어 (자신)", baseFormId = 11),

        // 12. 알고 있다 (싯테이루)
        KeigoExpression("知っている", "알고 있다", "しっている", "싯테이루", "기본형", baseFormId = 12),
        KeigoExpression("存じております", "알고 있습니다", "ぞんじております", "존지테 오리마스", "겸양어 (자신)", baseFormId = 12),
        KeigoExpression("ご存じです", "알고 계십니다", "ごぞんじです", "고존지데스", "존경어 (상대)", baseFormId = 12),

        // 13. 입다 (키루)
        KeigoExpression("着る", "입다", "きる", "키루", "기본형", baseFormId = 13),
        KeigoExpression("お召しになります", "입으십니다", "おめしになります", "오메시니 나리마스", "존경어 (상대)", baseFormId = 13),

        // 14. 자다 (네루)
        KeigoExpression("寝る", "자다", "ねる", "네루", "기본형", baseFormId = 14),
        KeigoExpression("お休みになります", "주무십니다", "おやすみになります", "오야스미니 나리마스", "존경어 (상대)", baseFormId = 14)
    )
}