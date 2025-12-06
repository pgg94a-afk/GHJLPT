package com.gg.ghjnpt.data

object GrammarData {
    val Grammars1 = listOf(
        Grammar("~さえ~ば", "N+さえ+Vば형 / 의문사 ~か형", "~만 ~하면", "~さえ~ば", "~사에~바"),
        Grammar("~といった", "N1, N2 といった N3", "~와/과 같은", "~といった", "~토잇타"),
        Grammar("~のこと", "N+のこと", "~에 관한 일, ~에 대해서", "~のこと", "~노코토"),
        Grammar("~はもちろん", "N+はもちろん", "~은/는 물론", "~はもちろん", "~와/모치론"),
        Grammar("~向け (~むけ)", "N+向け", "~용, ~을 위한, ~ 대상의", "~むけ", "~무케")
    )

    val Grammars2 = listOf(
        Grammar("~つもりだった", "Vた형 / Aい / Naな / Nの", "~했다고 생각했다, ~인 줄 알았다", "~つもりだった", "~츠모리닷타"),
        Grammar("~なあ", "보통형+なあ", "~구나, ~네", "~なあ", "~나아"),
        Grammar("~によると", "N+によると", "~에 의하면", "~によると", "~니 요루토"),
        Grammar("~のことだから", "N+のことだから", "~이니까, ~라는 (사람은) 당연히", "~のことだから", "~노코토다카라"),
        Grammar("~始める (~はじめる)", "Vます형+始める", "~하기 시작하다", "~はじめる", "~하지메루")
    )

    val Grammars3 = listOf(
        Grammar("~うちに", "V사전형·ない형·ている / Aい / Naな / Nの + うちに", "~하는 동안에, ~사이에, ~않는 동안에", "~うちに", "~우치니"),
        Grammar("~に決まっている (~にきまっている)", "보통형+に決まっている <Na / N>", "~임에 틀림없다", "~にきまっている", "~니 키맛테이루"),
        Grammar("~さえ", "N+に/から/で/の/と+さえ", "~에서, ~조차 / ~부터 / ~까지의", "~に/から/までの", "~니/카라/데/노/토 사에"),
        Grammar("~といえば", "N+といえば", "~라고 하면, ~라고 한다면", "~といえば", "~토이에바"),
        Grammar("~わけにはいかない", "V사전형·ない형+わけにはいかない", "~할 수는 없다 / ~하지 않을 수는 없다", "~わけにはいかない", "~와케니와 이카나이")
    )

    val Grammars4 = listOf(
        Grammar("~恐れがある (~おそれがある)", "V사전형·ない형·Nの+恐れがある", "~할 우려가 있다", "~おそれがある", "~오소레가 아루"),
        Grammar("~ことはない", "V사전형+ことはない", "~할 필요는 없다", "~ことはない", "~코토와 나이"),
        Grammar("~しかない", "V사전형 / N+しかない", "~할 수밖에 없다", "~しかない", "~시카 나이"),
        Grammar("~において / ~におけるN", "N+において / N+におけるN", "~에서, ~에 있어서 / ~에서의", "~において / ~における", "~니 오이테 / ~니 오케루"),
        Grammar("~わけだ", "보통형+わけだ <Naな / Nの>", "~인 것이다, ~일 수밖에. (납득, 당연한 결론)", "~わけだ", "~와케다")
    )

    val Grammars5 = listOf(
        Grammar("~てしょうがない", "Vて형 / Aくて / Naで+しょうがない", "너무 ~하다, ~해서 어쩔 수 없다", "~てしょうがない", "~테 쇼우가나이")
    )
    
    val Grammars6 = listOf(
        Grammar("~がち", "Vます形 / N+がち", "~하기 쉬움, ~하는 경향이 있음 (부정)", "~がち", "~가치"),
        Grammar("~っぽい", "Vます形 / Aい / N+っぽい", "~한 느낌이 든다, ~처럼 보인다", "~っぽい", "~ㅅ포이"),
        Grammar("~としたら / ~とすれば", "보통형+としたら / とすれば", "~라고 가정하면, ~라고 한다면", "~としたら / ~とすれば", "~토시타라 / ~토스레바"),
        Grammar("~反面、~ (~はんめん、~)", "보통형 <Naな / Nである>+反面", "~인 반면, ~", "~はんめん", "~한멘"),
        Grammar("~（よ）うとしない", "V의지형+としない", "~하려고 하지 않는다", "~（よ）うとしない", "~(요)우토시나이")
    )

    val Grammars7 = listOf(
        Grammar("~一方だ (~いっぽうだ)", "V사전형+一方だ", "점점 ~하다, ~하기만 하다", "~いっぽうだ", "~잇포우다"),
        Grammar("~からには", "보통형+からには <Naである / Nである>", "~한 이상에는, ~이니까", "~からには", "~카라니와"),
        Grammar("~に対して(は) / ~に対するN", "N+に対し(て) / ~に対するN", "~에 대해서(는) / ~에 대한N", "~にたいして(は) / ~にたいするN", "~니 타이시테(와) / ~니 타이스루N"),
        Grammar("~べきだ / ~べき", "V사전형+べきだ / べき (※する→すべき)", "~해야 한다", "~べきだ / ~べき", "~베키다 / ~베키"),
        Grammar("~を込めて (~をこめて)", "N+を込めて", "~을 담아서", "~をこめて", "~오 코메테")
    )

    val Grammars8 = listOf(
        Grammar("~かのように", "V사전형·た形+かのように", "(마치) ~인 것처럼, ~인 듯", "~かのように", "~카노요우니"),
        Grammar("~きる", "Vます形+きる", "전부 ~하다, 완전히 ~하다", "~きる", "~키루"),
        Grammar("~たて", "Vます形+たて", "막 ~한, 갓 ~한", "~たて", "~타테"),
        Grammar("~たとたん（に）", "Vた形+とたん（に）", "~하자마자, ~한 순간", "~たとたん（に）", "~타토탄(니)"),
        Grammar("~に比べて (~にくらべて)", "N+に比べて", "~에 비해서", "~にくらべて", "~니 쿠라베테")
    )

    val Grammars9 = listOf(
        Grammar("~てからでないと~ない", "Vて形+からでないと~ない", "~하고 나서가 아니면 ~않다/못한다", "~てからでないと~ない", "~테카라데나이토~나이"),
        Grammar("~てもらいたい / ~ていただきたい", "Vて形+もらいたい / いただきたい", "~해 주길 바란다, ~해 줬으면 좋겠다", "~てもらいたい / ~ていただきたい", "~테모라이타이 / ~테이타다키타이"),
        Grammar("~というより~", "보통형+というより <Na / N>", "~라기보다 ~", "~というより~", "~토 이우요리 ~"),
        Grammar("~ものだから", "보통형+ものだから <Naな / Nな>", "~해서, ~ 때문에 (이유, 변명)", "~ものだから", "~모노다카라"),
        Grammar("~ようなら / ~ようだったら", "V사전형·ない형/Aい/Naな/Nの+ようなら / ようだったら", "~할 것 같으면, 만약 ~하면", "~ようなら / ~ようだったら", "~요우나라 / ~요우닷타라")
    )

    val Grammars10 = listOf(
        Grammar("~（さ）せていただきます", "V사역て形+いただきます", "~하겠습니다 (겸양)", "~（さ）せていただきます", "~(사)세테이타다키마스"),
        Grammar("~ていただけますか / ~ていただけませんか / ~ていただけないでしょうか", "Vて形+いただけますか / いただけませんか / いただけないでしょうか", "~해 주시겠습니까? (정중한 의뢰)", "~ていただけますか / ...", "~테이타다케마스카 / ..."),
        Grammar("~ていらっしゃいます", "Vて형+이테/나데/명사데+いらっしゃいます", "~하고 계십니다 (「~ている」의 존경어)", "~ていらっしゃいます", "~테이랏샤이마스"),
        Grammar("~でございます", "Nで / Naで+ございます", "~입니다 (「~です」의 정중한 표현)", "~でございます", "~데고자이마스")
    )

    val Grammars11 = listOf(
        Grammar("~かけだ", "Vます형 + かけだ", "막 ~하다, ~하는 도중이다", "~かけだ", "~카케다"),
        Grammar("~ことに", "Vた형 / Aい / Naな + ことに", "~하게도", "~ことに", "~코토니"),
        Grammar("~てくれると言われる", "Vて형/V나이데형 + くれると言われる", "~해 달라는 말을 듣다, ~해 달라는 부탁을 받다", "~てくれるといわれる", "~테 쿠레루토 이와레루"),
        Grammar("~ないことはない", "Vない형 / Aくない / Naでない / Nでない + ことはない", "~하지 않는 것은 아니다, ~기는 하다", "~ないことはない", "~나이 코토와 나이")
    )

    val Grammars12 = listOf(
        Grammar("~ついでに", "V사전형·た형 / Nの + ついでに", "~하는 김에", "~ついでに", "~츠이데니"),
        Grammar("~ば~ほど~", "V조건+Vた형 / A조건+Aい / Na조건+Naな + ほど", "~할수록 ~", "~ば~ほど", "~바~호도"),
        Grammar("~めったにない", "Nは + めったにない", "~은/는 좀처럼 ~지 않다, ~은/는 거의 없다", "~めったにない", "~멧타니 나이"),
        Grammar("~わけではない", "보통형 + わけではない <Naな / Nの·な>", "~한 것은 아니다 (부분 부정)", "~わけではない", "~와케데와 나이")
    )

    val Grammars13 = listOf(
        Grammar("~際（に） (~さい（に）)", "V사전형·た형 / Nの + 際（に）", "~때, ~할 때에 (격식)", "~さい（に）", "~사이(니)"),
        Grammar("~ず（に）", "Vない형 + ず（に） <する→せず>", "~하지 않고", "~ず（に）", "~즈(니)"),
        Grammar("~ばよかった", "V(ば형) / Vない형(なければ) + よかった", "~하면 좋았을 텐데 (후회, 유감)", "~ばよかった", "~바 요캇타"),
        Grammar("~ように", "Nの / V보통형 <Naな / Nである> + ように", "~처럼, ~와 같이", "~ように", "~요우니")
    )

    val Grammars14 = listOf(
        Grammar("~代わりに (~かわりに)", "V사전형·ない형 / Nの / V,Aい,Naな보통형 + 代わりに", "~대신에", "~かわりに", "~카와리니"),
        Grammar("~ことになっている", "V사전형·ない형 + ことになっている", "~하기로 되어 있다 (규칙, 규정)", "~ことになっている", "~코토니 낫테이루"),
        Grammar("~（さ）せてもらえませんか", "V(사역)て형 + もらえませんか", "~하게 해주시겠습니까? (허락, 요청)", "~（さ）せてもらえませんか", "~(사)세테 모라에마센카"),
        Grammar("~とともに", "N / V사전형 + とともに", "~와/과 더불어, ~와/과 함께, ~함에 따라", "~とともに", "~토 토모니")
    )

    val Grammars16 = listOf(
        Grammar("~といい", "V사전형 + といい", "~하면 좋다 (희망)", "~といい", "~토 이이"),
        Grammar("~とはかぎらない", "보통형 + とはかぎらない", "~라고는 할 수 없다, ~인 것만은 아니다", "~とはかぎらない", "~토와 카기라나이"),
        Grammar("~にしたがって", "V사전형 / N + にしたがって", "~함에 따라 (변화)", "~にしたがって", "~니 시타갓테"),
        Grammar("~（よ）うとする", "V의지형 + とする", "~하려고 하다", "~（よ）うとする", "~(요)우토 스루")
    )

    val Grammars17 = listOf(
        Grammar("~くせに", "보통형 + くせに <Naな / Nの>", "~이면서, ~인 주제에 (비난, 불만)", "~くせに", "~쿠세니"),
        Grammar("~だけでなく", "N / 보통형 + だけでなく <Naだな / Nである>", "~뿐만 아니라", "~だけでなく", "~다케데나쿠"),
        Grammar("~につれて", "V사전형 / Nする + につれて", "~(함)에 따라(서) (비례한 변화)", "~につれて", "~니 츠레테"),
        Grammar("~を通して", "N + を通して", "~동안, ~내내 / ~을/를 통해(서)", "~をとおして", "~오 토오시테"),
        Grammar("~をもとに（して）", "N + をもとに（して）", "~을/를 기초로 하여, ~을/를 토대로", "~をもとに（して）", "~오 모토니 (시테)")
    )

    val Grammars18 = listOf(
        Grammar("~最中（に）", "Vている / Nの + 最中（に）", "한창 ~하는 중(에)", "~さいちゅう（に）", "~사이츄우(니)"),
        Grammar("~について", "N + について", "~에 대해(서)", "~について", "~니 츠이테"),
        Grammar("~ば…のに", "V/A/Na/N 조건형 + 보통형 + のに", "~하면 …텐데 (후회, 안타까움)", "~ば…のに", "~바…노니"),
        Grammar("~ほど", "V사전형·ない형 / Aい·くない / N + ほど", "~만큼, ~정도(로)", "~ほど", "~호도"),
        Grammar("~わりに（は）", "Nの / V/A/Na 보통형 + わりに（は） <Naだな>", "~에 비해서(는), ~(한) 것치고는", "~わりに（は）", "~와리니(와)")
    )

    val grammarList = listOf(
        Grammar(
            japaneseGrammar = "～から～にかけて",
            connection = "N + からN + にかけて",
            meaning = "~에서 ~에 걸쳐서",
            hiragana = "～から～にかけて",
            koreanPronounce = "카라 ~ 니카케테"
        ),
        Grammar(
            japaneseGrammar = "～てみたらどう（ですか）",
            connection = "Vて形 + みたらどう",
            meaning = "~해 보면 어때? (어때요?)",
            hiragana = "～てみたらどう（ですか）",
            koreanPronounce = "테미타라도우 (데스카)"
        ),
        Grammar(
            japaneseGrammar = "～として",
            connection = "N + として",
            meaning = "~ 로서",
            hiragana = "～として",
            koreanPronounce = "토시테"
        ),
        Grammar(
            japaneseGrammar = "～ほど～はない",
            connection = "N + ほど～はない",
            meaning = "~만큼 ~은 없다, ~정도로 ~는 아니다",
            hiragana = "～ほど～はない",
            koreanPronounce = "호도 ~하나이"
        ),
        Grammar(
            japaneseGrammar = "～ようがない",
            connection = "Vます形 + ようがない",
            meaning = "~할 방법이 없다",
            hiragana = "～ようがない",
            koreanPronounce = "요우가나이"
        ),
        Grammar(
            japaneseGrammar = "あまり～ない",
            connection = "",
            meaning = "별로 ~하지 않다",
            hiragana = "あまり～ない",
            koreanPronounce = "아마리~나이"
        ),
        Grammar(
            japaneseGrammar = "必ずしも～ない",
            connection = "",
            meaning = "반드시 ~하지는 않다",
            hiragana = "かならずしも～ない",
            koreanPronounce = "카나라즈시모 ~나이"
        ),
        Grammar(
            japaneseGrammar = "少しも～ない",
            connection = "",
            meaning = "조금도 ~하지 않다",
            hiragana = "すこしも～ない",
            koreanPronounce = "스코시모 ~나이"
        ),
        Grammar(
            japaneseGrammar = "たとえ～ても",
            connection = "",
            meaning = "설령 ~일지라도",
            hiragana = "たとえ～ても",
            koreanPronounce = "타토에  ~테모"
        ),
        Grammar(
            japaneseGrammar = "どんなに～ても",
            connection = "",
            meaning = "아무리 ~더라도",
            hiragana = "どんなに～ても",
            koreanPronounce = "돈나니 ~테모"
        ),
        Grammar(
            japaneseGrammar = "もしかしたら～かもしれない",
            connection = "",
            meaning = "어쩌면 ~일지도 모른다",
            hiragana = "もしかしたら～かもしれない",
            koreanPronounce = "모시카시타라 ~카모시레나이"
        ),
        Grammar(
            japaneseGrammar = "おそらく～だろう",
            connection = "",
            meaning = "아마 ~일 것이다",
            hiragana = "おそらく～だろう",
            koreanPronounce = "오소라쿠 ~다로우"
        ),
        Grammar(
            japaneseGrammar = "もっと～だろう",
            connection = "",
            meaning = "분명 ~일 것이다",
            hiragana = "もっと～だろう",
            koreanPronounce = "못토 ~다로우"
        ),
        Grammar(
            japaneseGrammar = "まるで～みたいだ",
            connection = "",
            meaning = "마치 ~같다",
            hiragana = "まるで～みたいだ",
            koreanPronounce = "마루데 ~미타이다"
        ),
        Grammar(
            japaneseGrammar = "せっかく",
            connection = "",
            meaning = "모처럼",
            hiragana = "せっかく",
            koreanPronounce = "세츠카쿠"
        ),
        Grammar(
            japaneseGrammar = "どうか",
            connection = "",
            meaning = "모쪼록",
            hiragana = "どうか",
            koreanPronounce = "도우카"
        ),
        Grammar(
            japaneseGrammar = "すっかり",
            connection = "",
            meaning = "완전히",
            hiragana = "すっかり",
            koreanPronounce = "슷카리"
        ),
        Grammar(
            japaneseGrammar = "～上",
            connection = "보통형 + 上",
            meaning = "~(한) 데다가",
            hiragana = "～うえ",
            koreanPronounce = "우에"
        ),
        Grammar(
            japaneseGrammar = "～だらけ",
            connection = "N + だらけ",
            meaning = "~투성이",
            hiragana = "～だらけ",
            koreanPronounce = "~다라케"
        ),
        Grammar(
            japaneseGrammar = "～とおり",
            connection = "V辞書形・た形 + とおり",
            meaning = "~한 대로",
            hiragana = "～とおり",
            koreanPronounce = "~토오리"
        ),
        Grammar(
            japaneseGrammar = "～のでしょうか",
            connection = "보통형 + のでしょうか",
            meaning = "~ 것일까요?",
            hiragana = "～のでしょうか",
            koreanPronounce = "~노데쇼우카"
        ),
        Grammar(
            japaneseGrammar = "決して～ない",
            connection = "",
            meaning = "결코 ~하지 않다",
            hiragana = "けっして～ない",
            koreanPronounce = "케츠시테 ~나이"
        ),
        Grammar(
            japaneseGrammar = "～てばかりいる",
            connection = "",
            meaning = "~하고만 있다",
            hiragana = "～てばかりいる",
            koreanPronounce = "~테바카리이루"
        ),
        Grammar(
            japaneseGrammar = "～てはじめて",
            connection = "",
            meaning = "~하고 나서야",
            hiragana = "～てはじめて",
            koreanPronounce = "~테하지메테"
        ),
        Grammar(
            japaneseGrammar = "全く～ない",
            connection = "",
            meaning = "전혀 ~하지 않다.",
            hiragana = "まったく～ない",
            koreanPronounce = "맛타쿠 ~나이"
        ),
        Grammar(
            japaneseGrammar = "～をきっかけに",
            connection = "",
            meaning = "~을 계기로",
            hiragana = "～をきっかけに",
            koreanPronounce = "~오킷카케니"
        ),
        Grammar(
            japaneseGrammar = "～くらい",
            connection = "",
            meaning = "~정도",
            hiragana = "～くらい",
            koreanPronounce = "~쿠라이"
        ),
        Grammar(
            japaneseGrammar = "～ことから",
            connection = "",
            meaning = "~것 부터",
            hiragana = "～ことから",
            koreanPronounce = "~코토카라"
        ),
        Grammar(
            japaneseGrammar = "～というのは",
            connection = "",
            meaning = "~라는 것은",
            hiragana = "～というのは",
            koreanPronounce = "~토이우노와"
        ),
        Grammar(
            japaneseGrammar = "～なんか",
            connection = "",
            meaning = "~따위",
            hiragana = "～なんか",
            koreanPronounce = "~난카"
        ),
        Grammar(
            japaneseGrammar = "～はずがない",
            connection = "",
            meaning = "~할 리가 없다",
            hiragana = "～はずがない",
            koreanPronounce = "~하즈가나이"
        ),
        Grammar(
            japaneseGrammar = "～こそ",
            connection = "",
            meaning = "~야말로",
            hiragana = "～こそ",
            koreanPronounce = "~코소"
        ),
        Grammar(
            japaneseGrammar = "～ことにしている",
            connection = "",
            meaning = "~하기로 하고 있다",
            hiragana = "～ことにしている",
            koreanPronounce = "~코토니시테이루"
        ),
        Grammar(
            japaneseGrammar = "～さ",
            connection = "",
            meaning = "~함 (명사화)",
            hiragana = "～さ",
            koreanPronounce = "~사"
        ),
        Grammar(
            japaneseGrammar = "～といっても",
            connection = "",
            meaning = "~라고는 해도",
            hiragana = "～といっても",
            koreanPronounce = "~토잇테모"
        ),
        Grammar(
            japaneseGrammar = "～ものだ",
            connection = "",
            meaning = "~하다니",
            hiragana = "～ものだ",
            koreanPronounce = "~모노다"
        ),
        Grammar(
            japaneseGrammar = "～ということだ",
            connection = "",
            meaning = "~라고 한다",
            hiragana = "～ということだ",
            koreanPronounce = "~토이우코토다"
        ),
        Grammar(
            japaneseGrammar = "～といわれている",
            connection = "",
            meaning = "~라고 알려져있다",
            hiragana = "～といわれている",
            koreanPronounce = "~토이와레테이루"
        ),
        Grammar(
            japaneseGrammar = "～ところに",
            connection = "",
            meaning = "~하는데",
            hiragana = "～ところに",
            koreanPronounce = "~토코로니"
        ),
        Grammar(
            japaneseGrammar = "～ないと",
            connection = "",
            meaning = "~하지 않으면",
            hiragana = "～ないと",
            koreanPronounce = "~나이토"
        ),
        Grammar(
            japaneseGrammar = "～にともなって",
            connection = "",
            meaning = "~함에 따라",
            hiragana = "～にともなって",
            koreanPronounce = "~니토모낫테"
        ),
        Grammar(
            japaneseGrammar = "～一方",
            connection = "",
            meaning = "~하는 한편",
            hiragana = "～いっぽう",
            koreanPronounce = "~잇포우"
        ),
        Grammar(
            japaneseGrammar = "～につき",
            connection = "",
            meaning = "~당",
            hiragana = "～につき",
            koreanPronounce = "~니츠키"
        ),
        Grammar(
            japaneseGrammar = "～に反し",
            connection = "",
            meaning = "~와 반대로",
            hiragana = "～にはんし",
            koreanPronounce = "~니한시"
        ),
        Grammar(
            japaneseGrammar = "～によって",
            connection = "",
            meaning = "~에 의해",
            hiragana = "～によって",
            koreanPronounce = "~니욧테"
        ),
        Grammar(
            japaneseGrammar = "～からといって",
            connection = "",
            meaning = "~라고 해서",
            hiragana = "～からといって",
            koreanPronounce = "~카라토잇테"
        ),
        Grammar(
            japaneseGrammar = "～っぱなし",
            connection = "",
            meaning = "~인 채로",
            hiragana = "～っぱなし",
            koreanPronounce = "~읏포나시"
        ),
        Grammar(
            japaneseGrammar = "～に関して",
            connection = "",
            meaning = "~에 관해서",
            hiragana = "～にかんして",
            koreanPronounce = "~니칸시테"
        ),
        Grammar(
            japaneseGrammar = "～に違いない",
            connection = "",
            meaning = "~임에 틀림없다",
            hiragana = "～にちがいない",
            koreanPronounce = "~니치가이나이"
        ),
        Grammar(
            japaneseGrammar = "～のは～からだ",
            connection = "",
            meaning = "~것은 ~이기 때문이다",
            hiragana = "～のは～からだ",
            koreanPronounce = "~노하 ~카라다"
        ),
        Grammar(
            japaneseGrammar = "～たところ",
            connection = "",
            meaning = "~했더니",
            hiragana = "～たところ",
            koreanPronounce = "~타토코로"
        ),
        Grammar(
            japaneseGrammar = "～としても",
            connection = "",
            meaning = "~라고 해도",
            hiragana = "～としても",
            koreanPronounce = "~토시테모"
        ),
        Grammar(
            japaneseGrammar = "～にしては",
            connection = "",
            meaning = "~치고는",
            hiragana = "～にしては",
            koreanPronounce = "~니시테하"
        ),
        Grammar(
            japaneseGrammar = "～のは～だ",
            connection = "",
            meaning = "~것은 ~이다",
            hiragana = "～のは～だ",
            koreanPronounce = "~노하 ~다"
        ),
        Grammar(
            japaneseGrammar = "～わけがない",
            connection = "",
            meaning = "~할 리가 없다",
            hiragana = "～わけがない",
            koreanPronounce = "~와케가나이"
        ),
        Grammar(
            japaneseGrammar = "～気味",
            connection = "",
            meaning = "~기미",
            hiragana = "～ぎみ",
            koreanPronounce = "~기미"
        ),
        Grammar(
            japaneseGrammar = "～ことだ",
            connection = "",
            meaning = "~것이다, ~해야 한다",
            hiragana = "～ことだ",
            koreanPronounce = "~코토다"
        ),
        Grammar(
            japaneseGrammar = "～せいで",
            connection = "",
            meaning = "~탓에",
            hiragana = "～せいで",
            koreanPronounce = "~세이데"
        ),
        Grammar(
            japaneseGrammar = "～たび",
            connection = "",
            meaning = "~할 때마다",
            hiragana = "～たび",
            koreanPronounce = "~타비"
        ),
        Grammar(
            japaneseGrammar = "～ようにしている",
            connection = "",
            meaning = "~하도록 하고있다",
            hiragana = "～ようにしている",
            koreanPronounce = "~요우니시테이루"
        ),
        Grammar(
            japaneseGrammar = "～おかげで",
            connection = "",
            meaning = "~덕분에",
            hiragana = "～おかげで",
            koreanPronounce = "~오카게데"
        ),
        Grammar(
            japaneseGrammar = "～からこそ",
            connection = "",
            meaning = "~이니까",
            hiragana = "～からこそ",
            koreanPronounce = "~카라코소"
        ),
        Grammar(
            japaneseGrammar = "～ことか",
            connection = "",
            meaning = "~했는지",
            hiragana = "～ことか",
            koreanPronounce = "~코토카"
        ),
        Grammar(
            japaneseGrammar = "～にとって",
            connection = "",
            meaning = "~에게 있어",
            hiragana = "～にとって",
            koreanPronounce = "~니톳테"
        )
    )
}