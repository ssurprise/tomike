package com.skx.tomike.tank.widget.activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.tomike.tank.R;
import com.skx.tomike.tank.widget.fragment.NestedViewPagerFragment;

import java.util.ArrayList;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_NSCROLLVIEW_VP;

/**
 * 描述 : NestedScrollView 和 ViewPager 配合使用。好处：不用自己处理滑动冲突；坏处：ViewPager 需要指定其高度。
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2018/12/21 4:24 PM
 */
@Route(path = ROUTE_PATH_NSCROLLVIEW_VP)
public class NestedScrollViewViewPagerActivity extends SkxBaseActivity<BaseViewModel> {

    private static final ArrayList<String> mContent = new ArrayList<>();

    @Override
    protected void initParams() {
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_nestedscrollview_viewpager;
    }

    @Override
    protected void initView() {
        ViewPager vpContent = findViewById(R.id.nestedScrollView_vpContent);
        NestedViewPagerAdapter nestedViewPagerAdapter = new NestedViewPagerAdapter(getSupportFragmentManager());
        vpContent.setAdapter(nestedViewPagerAdapter);

        TabLayout nestedScrollView_tab = findViewById(R.id.nestedScrollView_tab);
        nestedScrollView_tab.setupWithViewPager(vpContent, true);
    }

    static class NestedViewPagerAdapter extends FragmentStatePagerAdapter {

        NestedViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return NestedViewPagerFragment.newInstance(mContent.get(i));
        }

        @Override
        public int getCount() {
            return mContent.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "《道德经》" + (position + 1);
        }
    }

    static {
        mContent.add("曲则全，枉则直，洼则盈，敝则新，少则得，多则惑。是以圣人抱一为天下式。不自见，故明；不自是，故彰，不自伐，故有功；不自矜，故长。夫唯不争，故天下莫能与之争。古之所谓\"曲则全\"者，岂虚言哉？诚全而归之。");
        mContent.add("01.道可道，非常道。名可名，非常名。无名天地之始。有名万物之母。故常无欲以观其妙。常有欲以观" +
                "其徼。此两者同出而异名，同谓之玄。玄之又玄，众妙之门。" +
                "\n" +
                " \n" +
                "\n" +
                "02.天下皆知美之为美，斯恶矣；皆知善之为善，斯不善已。故有无相生，难易相成，长短相形，高下相" +
                "倾，音声相和，前後相随。是以圣人处无为之事，行不言之教。万物作焉而不辞。生而不有，为而不恃，" +
                "功成而弗居。夫唯弗居，是以不去。" +
                "\n" +
                " \n" +
                "\n" +
                "03.不尚贤， 使民不争。不贵难得之货，使民不为盗。不见可欲，使民心不乱。是以圣人之治，虚其心，" +
                "实其腹，弱其志，强其骨；常使民无知、无欲，使夫智者不敢为也。为无为，则无不治。" +
                "\n" +
                " \n" +
                "\n" +
                "04.道冲而用之，或不盈。渊兮似万物之宗。解其纷，和其光，同其尘，湛兮似或存。吾不知谁之子，象帝之先。" +
                "\n" +
                " \n" +
                "\n" +
                "05.天地不仁，以万物为刍狗。圣人不仁，以百姓为刍狗。天地之间，其犹橐迭乎？虚而不屈，动而愈出" +
                "。多言数穷，不如守中。" +
                "\n" +
                " \n" +
                "\n" +
                "06.谷神不死是谓玄牝。玄牝之门是谓天地根。绵绵若存，用之不勤。" +
                "\n" +
                " \n" +
                "\n" +
                "07.天长地久。天地所以能长且久者，以其不自生，故能长生。是以圣人後其身而身先，外其身而身存。" +
                "非以其无私邪！故能成其私。" +
                "\n" +
                " \n" +
                "\n" +
                "08.上善若水。水善利万物而不争，处众人之所恶，故几於道。居善地，心善渊，与善仁，言善信，正善" +
                "治，事善能，动善时。夫唯不争，故无尤。" +
                "\n" +
                " \n" +
                "\n" +
                "09.持而盈之不如其己；揣而锐之不可长保；金玉满堂莫之能守；富贵而骄，自遗其咎。功遂身退，天之道。" +
                "\n" +
                " \n" +
                "\n" +
                "10.载营魄抱一，能无离乎？专气致柔，能如婴儿乎？涤除玄览，能无疵乎？爱国治民，能无为乎？天门" +
                "开阖，能为雌乎？明白四达，能无知乎。" +
                "\n" +
                " \n" +
                "\n" +
                "11.三十幅共一毂，当其无，有车之用。埏埴以为器，当其无，有器之用。凿户牖以为室，当其无，有室" +
                "之用。故有之以为利，无之以为用。" +
                "\n" +
                " \n" +
                "\n" +
                "12.五色令人目盲，五音令人耳聋，五味令人口爽，驰骋畋猎令人心发狂，难得之货令人行妨。是以圣人" +
                "，为腹不为目，故去彼取此。\n" +
                "\n" +
                " \n" +
                "\n" +
                "13.宠辱若惊，贵大患若身。何谓宠辱若惊？宠为下。得之若惊失之若惊是谓宠辱若惊。何谓贵大患若身" +
                "？吾所以有大患者，为吾有身，及吾无身，吾有何患。故贵以身为天下，若可寄天下。爱以身为天下，若" +
                "可托天下。" +
                "\n" +
                " \n" +
                "\n" +
                "14.视之不见名曰夷。听之不闻名曰希。抟之不得名曰微。此三者不可致诘，故混而为一。其上不皦" +
                "(jiǎo)，其下不昧，绳绳不可名，复归於无物。是谓无状之状，无物之象，是谓惚恍。迎之不见其首，随" +
                "之不见其後。执古之道以御今之有。能知古始，是谓道纪。" +
                "\n" +
                " \n" +
                "\n" +
                "15.古之善为士者，微妙玄通，深不可识。夫唯不可识，故强为之容。豫兮若冬涉川；犹兮若畏四邻；俨" +
                "兮其若容；涣兮若冰之将释；敦兮其若朴；旷兮其若谷；混兮其若浊；澹兮其若海；飉(liáo,风的声音)兮" +
                "若无止。孰能浊以静之徐清。孰能安以动之徐生。保此道者不欲盈。夫唯不盈故能蔽而新成。" +
                "\n" +
                " \n" +
                "\n" +
                "16.致虚极守静笃。万物并作，吾以观复。夫物芸芸各复归其根。归根曰静，是谓复命；复命曰常，知常" +
                "曰明。不知常，妄作凶。知常容，容乃公，公乃全，全乃天，天乃道，道乃久，没身不殆。" +
                "\n" +
                " \n" +
                "\n" +
                "17.太上，下知有之。其次，亲而誉之。其次，畏之。其次，侮之。信不足焉，有不信焉。悠兮其贵言，" +
                "功成事遂，百姓皆谓∶我自然。" +
                "\n" +
                " \n" +
                "\n" +
                "18.大道废有仁义；慧智出有大伪；六亲不和有孝慈；国家昏乱有忠臣。" +
                "\n" +
                " \n" +
                "\n" +
                "19.绝圣弃智，民利百倍；绝仁弃义，民复孝慈；绝巧弃利，盗贼无有；此三者，以为文不足。故令有所" +
                "属，见素抱朴少私寡欲。" +
                "\n" +
                " \n" +
                "\n" +
                "20.绝学无忧，唯之与阿，相去几何？善之与恶，相去若何？人之所畏，不可不畏。荒兮其未央哉！众人" +
                "熙熙如享太牢、如春登台。我独泊兮其未兆，如婴儿之未孩；儡儡(lěi,羸弱)兮若无所归。众人皆有馀，而" +
                "我独若遗。我愚人之心也哉！沌沌兮。俗人昭昭，我独昏昏；俗人察察，我独闷闷。众人皆有以，而我独" +
                "顽且鄙。我独异於人，而贵食母。" +
                "\n" +
                " \n" +
                "\n" +
                "21.孔德之容惟道是从。道之为物惟恍惟惚。惚兮恍兮其中有象。恍兮惚兮其中有物。窈兮冥兮其中有精" +
                "。其精甚真。其中有信。自古及今，其名不去以阅众甫。吾何以知众甫之状哉！以此。" +
                "\n" +
                " \n" +
                "\n" +
                "22.曲则全，枉则直，洼则盈，敝则新少则得，多则惑。是以圣人抱一为天下式。不自见故明；不自是故" +
                "彰；不自伐故有功；不自矜故长；夫唯不争，故天下莫能与之争。古之所谓∶\uE426曲则全者」岂虚言哉！诚" +
                "全而归之。" +
                "\n" +
                " \n" +
                "\n" +
                "23.希言自然。故飘风不终朝，骤雨不终日。孰为此者？天地。天地尚不能久，而况於人乎？故从事於道" +
                "者，同於道。德者同於德。失者同於失。同於道者道亦乐得之；同於德者德亦乐得之；同於失者失於乐得" +
                "之信不足焉有不信焉。" +
                "\n" +
                " \n" +
                "\n" +
                "24.企者不立；跨者不行。自见者不明；自是者不彰。自伐者无功；自矜者不长。其在道也曰∶馀食赘形" +
                "。物或恶之，故有道者不处。" +
                "\n" +
                " \n" +
                "\n" +
                "25.有物混成先天地生。寂兮寥兮独立不改，周行而不殆，可以为天下母。吾不知其名，强字之曰道。强" +
                "为之名曰大。大曰逝，逝曰远，远曰反。故道大、天大、地大、人亦大。域中有大，而人居其一焉。人法" +
                "地，地法天，天法道，道法自然。" +
                "\n" +
                " \n" +
                "\n" +
                "26.重为轻根，静为躁君。是以君子终日行不离轻重。虽有荣观燕处超然。奈何万乘之主而以身轻天下。" +
                "轻则失根，躁则失君。" +
                "\n" +
                " \n" +
                "\n" +
                "27.善行无辙迹。善言无瑕谪。善数不用筹策。善闭无关楗而不可开。善结无绳约而不可解。是以圣人常" +
                "善救人，故无弃人。常善救物，故无弃物。是谓袭明。故善人者不善人之师。不善人者善人之资。不贵其" +
                "师、不爱其资，虽智大迷，是谓要妙。" +
                "\n" +
                " \n" +
                "\n" +
                "28.知其雄，守其雌，为天下溪。为天下溪，常德不离，复归於婴儿。知其白，守其黑，为天下式。为天\n" +
                "下式，常德不忒，复归於无极。知其荣，守其辱，为天下谷。为天下谷，常德乃足，复归於朴。朴散则为\n" +
                "\n" +
                "器，圣人用之则为官长。故大制不割。\n" +
                "\n" +
                " \n" +
                "\n" +
                "29.将欲取天下而为之，吾见其不得已。天下神器，不可为也，为者败之，执者失之。夫物或行或随、或\n" +
                "\n" +
                "觑或吹、或强或羸、或挫或隳。是以圣人去甚、去奢、去泰。\n" +
                "\n" +
                " \n" +
                "\n" +
                "30.以道佐人主者，不以兵强天下。其事好还。师之所处荆棘生焉。军之後必有凶年。善有果而已，不敢\n" +
                "\n" +
                "以取强。果而勿矜。果而勿伐。果而勿骄。果而不得已。果而勿强。物壮则老，是谓不道，不道早已。\n" +
                "\n" +
                " \n" +
                "\n" +
                "31.夫佳兵者不祥之器，物或恶之，故有道者不处。君子居则贵左，用兵则贵右。兵者不祥之器，非君子\n" +
                "\n" +
                "之器，不得已而用之，恬淡为上。胜而不美，而美之者，是乐杀人。夫乐杀人者，则不可得志於天下矣。\n" +
                "\n" +
                "吉事尚左，凶事尚右。偏将军居左，上将军居右。言以丧礼处之。杀人之众，以悲哀泣之，战胜以丧礼处\n" +
                "\n" +
                "之。\n" +
                "\n" +
                " \n" +
                "\n" +
                "32.道常无名。朴虽小天下莫能臣也。侯王若能守之，万物将自宾。天地相合以降甘露，民莫之令而自均\n" +
                "\n" +
                "。始制有名，名亦既有，夫亦将知止，知止可以不殆。譬道之在天下，犹川谷之於江海。\n" +
                "\n" +
                " \n" +
                "\n" +
                "33.知人者智，自知者明。胜人者有力，自胜者强。知足者富。强行者有志。不失其所者久。死而不亡者\n" +
                "\n" +
                "，寿。\n" +
                "\n" +
                " \n" +
                "\n" +
                "34.大道泛兮，其可左右。万物恃之以生而不辞，功成而不名有。衣养万物而不为主，常无欲可名於小。\n" +
                "\n" +
                "万物归焉，而不为主，可名为大。以其终不自为大，故能成其大。\n" +
                "\n" +
                " \n" +
                "\n" +
                "35.执大象天下往。往而不害安平太。乐与饵，过客止。道之出口淡乎其无味。视之不足见。听之不足闻\n" +
                "\n" +
                "。用之不足既。\n" +
                "\n" +
                " \n" +
                "\n" +
                "36.将欲歙之，必固张之。将欲弱之，必固强之。将欲废之，必固兴之。将欲取之，必固与之。是谓微明\n" +
                "\n" +
                "。柔弱胜刚强。鱼不可脱於渊，国之利器不可以示人。\n" +
                "\n" +
                " \n" +
                "\n" +
                "37.道常无为，而无不为。侯王若能守之，万物将自化。化而欲作，吾将镇之以无名之朴。无名之朴，夫\n" +
                "\n" +
                "亦将无欲。不欲以静，天下将自定。\n" +
                "\n" +
                " \n" +
                "\n" +
                "38.上德不德是以有德。下德不失德是以无德。上德无为而无以为。下德无为而有以为。上仁为之而无以\n" +
                "\n" +
                "为。上义为之而有以为。上礼为之而莫之以应，则攘臂而扔之。故失道而後德。失德而後仁。失仁而後义\n" +
                "\n" +
                "。失义而後礼。夫礼者忠信之薄而乱之首。前识者，道之华而愚之始。是以大丈夫，处其厚不居其薄。处\n" +
                "\n" +
                "其实，不居其华。故去彼取此。\n" +
                "\n" +
                " \n" +
                "\n" +
                "39.昔之得一者。天得一以清。地得一以宁。神得一以灵。谷得一以盈。万物得一以生。侯王得一以为天\n" +
                "\n" +
                "下贞。其致之。天无以清将恐裂。地无以宁将恐废。神无以灵将恐歇。谷无以盈将恐竭。万物无以生将恐\n" +
                "\n" +
                "灭。侯王无以贞将恐蹶。故贵以贱为本，高以下为基。是以侯王自称孤、寡、不谷。此非以贱为本邪？非\n" +
                "\n" +
                "乎。至誉无誉。不欲琭琭如玉，珞珞如石。\n" +
                "\n" +
                " \n" +
                "\n" +
                "40.反者道之动。弱者道之用。天下万物生於有，有生於无。\n" +
                "\n" +
                " \n" +
                "\n" +
                "41.上士闻道勤而行之。中士闻道若存若亡。下士闻道大笑之。不笑不足以为道。故建言有之。明道若昧\n" +
                "\n" +
                "。进道若退。夷道若纇。上德若谷。大白若辱。广德若不足。建德若偷。质真若渝。大方无隅。大器晚成\n" +
                "\n" +
                "。大音希声。大象无形。道隐无名。夫唯道善贷且成。\n" +
                "\n" +
                " \n" +
                "\n" +
                "42.道生一。一生二。二生三。三生万物。万物负阴而抱阳，冲气以为和。人之所恶，唯孤、寡不谷，而\n" +
                "\n" +
                "王公以为称，故物或损之而益，或益之而损。人之所教，我亦教之，强梁者，不得其死。吾将以为教父。\n" +
                "\n" +
                " \n" +
                "\n" +
                "43.天下之至柔，驰骋天下之至坚。无有入无间，吾是以知无为之有益。不言之教，无为之益天下希及之。\n" +
                "\n" +
                " \n" +
                "\n" +
                "44.名与身孰亲。身与货孰多。得与亡孰病。是故甚爱必大费。多藏必厚亡。知足不辱。知止不殆。可以\n" +
                "\n" +
                "长久。\n" +
                "\n" +
                " \n" +
                "\n" +
                "45.大成若缺，其用不弊。大盈若冲，其用不穷。大直若屈。大巧若拙。大辩若讷。静胜躁，寒胜热。清\n" +
                "\n" +
                "静为天下正。\n" +
                "\n" +
                " \n" +
                "\n" +
                "46.天下有道，却走马以粪。天下无道，戎马生於郊。祸莫大於不知足。咎莫大於欲得。故知足之足常足\n" +
                "\n" +
                "矣。\n" +
                "\n" +
                " \n" +
                "\n" +
                "47.不出户知天下。不窥牖见天道。其出弥远，其知弥少。是以圣人不行而知。不见而明。不为而成。\n" +
                "\n" +
                " \n" +
                "\n" +
                "48.为学日益。为道日损。损之又损，以至於无为。无为而不为。取天下常以无事，及其有事，不足以取\n" +
                "\n" +
                "天下。\n" +
                "\n" +
                " \n" +
                "\n" +
                "49.圣人无常心。以百姓心为心。善者吾善之。不善者吾亦善之，德善。信者吾信之。不信者吾亦信之，\n" +
                "\n" +
                "德信。圣人在天下，歙歙(xīxī,无所偏执的样子)焉，为天下浑其心。百姓皆注其耳目，圣人皆孩之。\n" +
                "\n" +
                " \n" +
                "\n" +
                "50.出生入死。生之徒，十有三。死之徒，十有三。人之生，动之於死地，亦十有三。夫何故？以其生生\n" +
                "\n" +
                "之厚。盖闻善摄生者，陆行不遇凶虎，入军不被甲兵。凶无所投其角。虎无所用其爪。兵无所容其刃。夫\n" +
                "\n" +
                "何故？以其无死地。\n" +
                "\n" +
                " \n" +
                "\n" +
                "51.道生之，德畜之，物形之，势成之。是以万物莫不尊道，而贵德。道之尊，德之贵，夫莫之命而常自\n" +
                "\n" +
                "然。故道生之，德畜之。长之育之。亭之毒之。养之覆之。生而不有，为而不恃，长而不宰。是谓玄德。\n" +
                "\n" +
                " \n" +
                "\n" +
                "52.天下有始，以为天下母。既得其母，以知其子。既知其子，复守其母，没身不殆。塞其兑，闭其门，\n" +
                "\n" +
                "终身不勤。开其兑，济其事，终身不救。见其小曰明，守柔曰强。用其光，复归其明，无遗身殃。是为习\n" +
                "\n" +
                "常。\n" +
                "\n" +
                " \n" +
                "\n" +
                "53.使我介然有知，行於大道，唯施是畏。大道甚夷，而人好径。朝甚除，田甚芜，仓甚虚。服文彩，带\n" +
                "\n" +
                "利剑，厌饮食，财货有馀。是谓盗夸。非道也哉。\n" +
                "\n" +
                " \n" +
                "\n" +
                "54.善建者不拔。善抱者不脱。子孙以祭祀不辍。修之於身其德乃真。修之於家其德乃馀。修之於乡其德\n" +
                "\n" +
                "乃长。修之於邦其德乃丰。修之於天下其德乃普。故以身观身，以家观家，以乡观乡，以邦观邦，以天下\n" +
                "\n" +
                "观天下。吾何以知天下然哉？以此。\n" +
                "\n" +
                " \n" +
                "\n" +
                "55.含德之厚比於赤子。毒虫不螫，猛兽不据，攫鸟不抟。骨弱筋柔而握固。未知牝牡之合而全作，精之\n" +
                "\n" +
                "至也。终日号而不嗄，和之至也。知和曰常。知常曰明。益生曰祥。心使气曰强。物壮则老。谓之不道，\n" +
                "\n" +
                "不道早已。\n" +
                "\n" +
                " \n" +
                "\n" +
                "56.知者不言。言者不知。挫其锐，解其纷，和其光，同其尘，是谓玄同。故不可得而亲。不可得而疏。\n" +
                "\n" +
                "不可得而利。不可得而害。不可得而贵。不可得而贱。故为天下贵。\n" +
                "\n" +
                " \n" +
                "\n" +
                "57.以正治国，以奇用兵，以无事取天下。吾何以知其然哉？以此。天下多忌讳而民弥贫。民多利器国家\n" +
                "\n" +
                "滋昏。人多伎巧奇物泫起。法令滋彰盗贼多有。故圣人云我无为而民自化。我好静而民自正。我无事而民\n" +
                "\n" +
                "自富。我无欲而民自朴。\n" +
                "\n" +
                " \n" +
                "\n" +
                "58.其政闷闷，其民淳淳。其政察察，其民缺缺。祸尚福之所倚。福尚祸之所伏。孰知其极，其无正。正\n" +
                "\n" +
                "复为奇，善复为妖。人之迷其日固久。是以圣人方而不割。廉而不刿。直而不肆。光而不耀。\n" +
                "\n" +
                " \n" +
                "\n" +
                "59.治人事天莫若啬。夫唯啬是谓早服。早服谓之重积德。重积德则无不克。无不克则莫知其极。莫知其\n" +
                "\n" +
                "极可以有国。有国之母可以长久。是谓深根固柢，长生久视之道。\n" +
                "\n" +
                " \n" +
                "\n" +
                "60.治大国若烹小鲜。以道莅天下，其迨ㄞ哄非其鬼不神，其神不伤人。非其神不伤人，圣人亦不伤人。\n" +
                "\n" +
                "夫两不相伤，故德交归焉。\n" +
                "\n" +
                " \n" +
                "\n" +
                "61.大国者下流，天下之交。天下之牝。牝常以静胜牡。以静为下。故大国以下小国，则取小国。小国以\n" +
                "\n" +
                "下大国，则取大国。故或下以取，或下而取。大国不过欲兼畜人。小国不过欲入事人。夫两者各得所欲，\n" +
                "\n" +
                "大者宜为下。\n" +
                "\n" +
                " \n" +
                "\n" +
                "62.道者万物之奥。善人之宝，不善人之所保。美言可以市尊。美行可以加人。人之不善，何弃之有。故\n" +
                "\n" +
                "立天子、置三公，虽有拱璧以先驷马，不如坐进此道。古之所以贵此道者何。不曰∶求以得，有罪以免邪\n" +
                "\n" +
                "？故为天下贵。\n" +
                "\n" +
                " \n" +
                "\n" +
                "63.为无为，事无事，味无味。大小多少，报怨以德。图难於其易，为大於其细。天下难事必作於易。天\n" +
                "\n" +
                "下大事必作於细。是以圣人终不为大，故能成其大。夫轻诺必寡信。多易必多难。是以圣人犹难之，故终\n" +
                "\n" +
                "无难矣。\n" +
                "\n" +
                " \n" +
                "\n" +
                "64.其安易持，其未兆易谋。其脆易泮，其微易散。为之於未有，治之於未乱。合抱之木生於毫末。九层\n" +
                "\n" +
                "之台起於累土。千里之行始於足下。为者败之，执者失之。是以圣人无为故无败，无执故无失。民之从事\n" +
                "\n" +
                "常於几成而败之。慎终如始则无败事。是以圣人欲不欲，不贵难得之货。学不学，复众人之所过，以辅万\n" +
                "\n" +
                "物之自然而不敢为。\n" +
                "\n" +
                " \n" +
                "\n" +
                "65.古之善为道者，非以明民，将以愚之。民之难治，以其智多。故以智治国，国之贼。不以智治国，国\n" +
                "\n" +
                "之福。知此两者，亦稽式。常知稽式，是谓玄德。玄德深矣、远矣！与物反矣。然後乃至大顺。\n" +
                "\n" +
                " \n" +
                "\n" +
                "66.江海之所以能为百谷王者，以其善下之，故能为百谷王。是以圣人欲上民，必以言下之。欲先民，必\n" +
                "\n" +
                "以身後之。是以圣人处上而民不重，处前而民不害。是以天下乐推而不厌。以其不争，故天下莫能与之争。\n" +
                "\n" +
                " \n" +
                "\n" +
                "67.天下皆谓我道大似不肖。夫唯大故似不肖。若肖，久矣！其细也夫。我有三宝持而保之∶一曰慈， 二\n" +
                "\n" +
                "曰俭，三曰不敢为天下先。慈故能勇，俭故能广，不敢为天下先故能成器长。今舍慈且勇，舍俭且广，舍\n" +
                "\n" +
                "後且先，死矣！夫慈以战则胜，以守则固。天将救之以慈卫之。\n" +
                "\n" +
                " \n" +
                "\n" +
                "68.善为士者不武。善战者不怒。善胜敌者不与。善用人者为之下。是谓不争之德。是谓用人之力。是谓\n" +
                "\n" +
                "配天之极。\n" +
                "\n" +
                " \n" +
                "\n" +
                "69.用兵有言，吾不敢为主而为客。不敢进寸而退尺。是谓行无行。攘无臂。扔无敌。执无兵。祸莫大於\n" +
                "\n" +
                "轻敌。轻敌几丧吾宝。故抗兵相加哀者胜矣。\n" +
                "\n" +
                " \n" +
                "\n" +
                "70.吾言甚易知、甚易行。天下莫能知、莫能行。言有宗、事有君。夫唯无知，是以我不知。知我者希，\n" +
                "\n" +
                "则我者贵。是以圣被褐怀玉。\n" +
                "\n" +
                " \n" +
                "\n" +
                "71.知不知上，不知知病。夫唯病病，是以不病。圣人不病，以其病病。夫唯病病，是以不病。\n" +
                "\n" +
                " \n" +
                "\n" +
                "72.民不畏威，则大威至。无狎其所居，无厌其所生。夫唯不厌，是以不厌。是以圣人自知不自见。自爱\n" +
                "\n" +
                "不自贵。故去彼取此。\n" +
                "\n" +
                " \n" +
                "\n" +
                "73.勇於敢则杀。勇於不敢则活。此两者或利或害。天之所恶孰知其故。天之道不争而善胜。不言而善应\n" +
                "\n" +
                "。不召而自来。繟(chǎn,舒缓)然而善谋。天网恢恢疏而不失。\n" +
                "\n" +
                " \n" +
                "\n" +
                "74.民不畏死，奈何以死惧之。若使民常畏死，而为奇者，吾得执而杀之，孰敢。常有司杀者杀。夫代司\n" +
                "\n" +
                "杀者杀，是谓代大匠斫。夫代大匠斫者，希有不伤其手矣。\n" +
                "\n" +
                " \n" +
                "\n" +
                "75.民之饥以其上食税之多，是以饥。民之难治以其上之有为，是以难治。民之轻死以其求生之厚，是以\n" +
                "\n" +
                "轻死。夫唯无以生为者，是贤於贵生。\n" +
                "\n" +
                " \n" +
                "\n" +
                "76.人之生也柔弱，其死也坚强。草木之生也柔脆，其死也枯槁。故坚强者死之徒，柔弱者生之徒。是以\n" +
                "\n" +
                "兵强则灭，木强则折。强大处下，柔弱处上。\n" +
                "\n" +
                " \n" +
                "\n" +
                "77.天之道其犹张弓与。高者抑之，下者举之。有馀者损之，不足者补之。天之道，损有馀而补不足。人\n" +
                "\n" +
                "之道，则不然，损不足以奉有馀。孰能有馀以奉天下，唯有道者。是以圣人为而不恃，功成而不处。其不\n" +
                "\n" +
                "欲见贤邪！\n" +
                "\n" +
                " \n" +
                "\n" +
                "78.天下莫柔弱於水。而攻坚强者，莫之能胜。以其无以易之。弱之胜强。柔之胜刚。天下莫不知莫能行\n" +
                "\n" +
                "。是以圣人云，受国之垢是谓社稷主。受国不祥是为天下王。正言若反。\n" +
                "\n" +
                " \n" +
                "\n" +
                "79.和大怨必有馀怨，安可以为善。是以圣人执左契，而不责於人。有德司契，无德司彻。天道无亲常与\n" +
                "\n" +
                "善人。\n" +
                "\n" +
                " \n" +
                "\n" +
                "80.小国寡民。使有什伯之器而不用。使民重死而不远徙。虽有舟舆无所乘之。虽有甲兵无所陈之。使民\n" +
                "\n" +
                "复结绳而用之。甘其食、美其服、安其居、乐其俗。邻国相望，鸡犬之声相闻。民至老死不相往来。\n" +
                "\n" +
                " \n" +
                "\n" +
                "81.信言不美。美言不信。善者不辩。辩者不善。知者不博。博者不知。圣人不积。既以为人己愈有。既\n" +
                "\n" +
                "以与人己愈多。天之道利而不害。圣人之道为而不争。");
        mContent.add("唯之与阿①，相去几何？美之与恶②，相去若何？人之所畏③，不可不畏。荒兮④，其未央哉⑤！众人熙熙⑥，如享太牢⑦，如春登台⑧。我⑨独泊兮⑩，其未兆⑾；沌沌兮⑿，如婴儿之未孩⒀；傫傫兮⒁，若无所归。众人皆有余⒂，而我独若遗⒃。我愚人之心也哉⒄！俗人昭昭⒅，我独昏昏⒆。俗人察察⒇，我独闷闷[21]。澹兮[22]，其若海；飂兮[23]，若无止。众人皆有以[24]，而我独顽且鄙[25]。我独异于人，而贵食母[26]。");
        mContent.add("上士闻道，勤而行之；中士闻道，若存若亡；下士闻道，大笑之。不笑不足以为道。故建言①有之：明道若昧，进道若退，夷道若纇②。上德若谷；大白若辱③；广德若不足；建德若偷④；质真若渝⑤。大方无隅⑥；大器晚成；大音希声；大象无形；道隐无名。夫唯道，善贷且成⑦。");
        mContent.add("人之生也柔弱①，其死也坚强②。草木③之生也柔脆④，其死也枯槁⑤。故坚强者死之徒⑥，柔弱者生之徒⑦。是以兵强则灭，木强则折⑧。强大处下，柔弱处上。");
    }
}
