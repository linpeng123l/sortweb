package com.dreamer.serverold;

import com.dreamer.db.DbUtil;
import com.dreamer.domain.KeyWordCountOld;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by linpeng123l on 2016/10/17.
 */
public class KeyWordsService {

    static String[] notInWords = new String[]{"data mining",
            "association rules",
            "association rule",
            "association rule mining",
            "frequent itemsets",
            "association rules mining",
            "knowledge discovery",
            "confidence",
            "support",
            "algorithm",
            "apriori",
            "frequent itemset",
            "databases",
            "frequent patterns",
            "machine learning",
            "algorithms",
            "apriori algorithm",
            "patterns",
            "mining",
            "correlation",
            "discovery"};

    private static List<KeyWordCountOld> queryAllKeyWords() {
        Connection connection = DbUtil.openConnection();
        try {
            List<KeyWordCountOld> keyWords = new ArrayList<>();
            String sql = "SELECT LOWER(key_words) key_words,COUNT(*) count FROM key_words where key_words not in " +
                    "(" + inString(notInWords) + ")" +
                    " GROUP BY LOWER(key_words) ORDER BY count DESC";
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KeyWordCountOld keyWordCount = new KeyWordCountOld();
                keyWordCount.setKey_words(rs.getString("key_words"));
                keyWordCount.setCount(rs.getInt("count"));
                keyWords.add(keyWordCount);
            }
            return keyWords;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库查询出错");
        }
    }

    public static void insertKeyWordCount(List<KeyWordCountOld> keyWordCounts) {
        Connection connection = DbUtil.openConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO KEY_WORDS_COUNT('key_words','count','content','category') VALUES (?,?,?,?))");
            for (KeyWordCountOld keyWordCount : keyWordCounts) {
                ps.setString(1, keyWordCount.getKey_words());
                ps.setInt(2, keyWordCount.getCount());
                ps.setString(3, keyWordCount.getContent());
                ps.setString(4, keyWordCount.getCategory());
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<KeyWordCountOld> keywords() {
        List<KeyWordCountOld> keyWordCounts = queryAllKeyWords();
        List<KeyWordCountOld> newKeyWordCounts = new ArrayList<KeyWordCountOld>();

        KeyWordCountOld fuzzy = new KeyWordCountOld();
        fuzzy.setKey_words("fuzzy association rules");
        fuzzy.setContent("模糊关联规则，出现频次125。一般的关联规则无法处理涉及连续值得记录，有些算法通过区间划分将连续值离散化，但是这种划分过于僵硬，对边界数据不是很友好。模糊关联规则就是应用模糊集来软化属性论域的划分边界。");
        fuzzy.setCategory("扩展");
        newKeyWordCounts.add(fuzzy);

        KeyWordCountOld associativeClassification = new KeyWordCountOld();
        associativeClassification.setKey_words("associative classification");
        associativeClassification.setContent("");
        associativeClassification.setCategory("");
        newKeyWordCounts.add(associativeClassification);

        KeyWordCountOld clustering = new KeyWordCountOld();
        clustering.setKey_words("clustering");
        clustering.setContent("一般将关联规则和聚类联合起来用于解决特定问题,比如图像检索研究");
        newKeyWordCounts.add(clustering);

        KeyWordCountOld genetic = new KeyWordCountOld();
        genetic.setKey_words("genetic algorithm");
        genetic.setContent("遗传算法");
        newKeyWordCounts.add(genetic);

        KeyWordCountOld quantitative = new KeyWordCountOld();
        quantitative.setKey_words("quantitative association rules");
        quantitative.setContent("定量关联规则,区别于定性关联规则,比如有属性是年龄/成绩等,是关联规则的一种分类");
        newKeyWordCounts.add(quantitative);

        KeyWordCountOld interestingness = new KeyWordCountOld();
        interestingness.setKey_words("interestingness");
        interestingness.setContent("兴趣度关联规则,添加一个兴趣度阈值,更好的找到有用的关联规则,是关联规则的一种分类");
        newKeyWordCounts.add(interestingness);

        KeyWordCountOld distributed = new KeyWordCountOld();
        distributed.setKey_words("distributed data mining");
        distributed.setContent("分布式数据挖掘,用于海量数据中发现关联规则");
        newKeyWordCounts.add(distributed);


        KeyWordCountOld parallel = new KeyWordCountOld();
        parallel.setKey_words("parallel computing");
        parallel.setContent("并行计算,数据挖掘,用于大数据中发现关联规则");
        newKeyWordCounts.add(parallel);

        KeyWordCountOld generalized = new KeyWordCountOld();
        generalized.setKey_words("generalized association rules");
        generalized.setContent("广义关联规则,在多层次上挖掘关联规则,关联规则一种扩展分类");
        newKeyWordCounts.add(generalized);

        KeyWordCountOld intrusion = new KeyWordCountOld();
        intrusion.setKey_words("intrusion detection");
        intrusion.setContent("入侵检测,使用关联规则,是关联规则的一种用途");
        newKeyWordCounts.add(intrusion);

        KeyWordCountOld text = new KeyWordCountOld();
        text.setKey_words("text mining");
        text.setContent("文本中挖掘关联规则,一种用途");
        newKeyWordCounts.add(text);

        KeyWordCountOld rough = new KeyWordCountOld();
        rough.setKey_words("rough set");
        rough.setContent("关联规则挖掘是数据挖掘中的一个重要问题，在最近几年被广泛研究。本文将粗糙集理论及方法引入高校教\n" +
                "师成长信息，通过属性约简降低属性纬数，然后基于粗糙集理论进行关联规则挖掘，得出了一些有益的结论，为拓展粗糙集\n" +
                "的应用领域做出了有益的探索。 ");
        newKeyWordCounts.add(rough);

        KeyWordCountOld spatial = new KeyWordCountOld();
        spatial.setKey_words("spatial data mining");
        spatial.setContent("随着空间数据获取技术的进步，空间数据量日益增大，已超出人们的分析能力。传统的空\n" +
                "间数据分析方法只能进行简单的数据分析，无法满足人们获取知识的需要。空间关联规则是空间\n" +
                "数据挖掘一个基本的任务，是从具有海量、多维、多尺度、不确定性边界等特性的空间数据中进行\n" +
                "知识发现的重要方法。本");
        newKeyWordCounts.add(spatial);

        KeyWordCountOld weighted = new KeyWordCountOld();
        weighted.setContent("加权关联规则,关联规则的一种扩展分类");
        weighted.setKey_words("weighted association rules");
        newKeyWordCounts.add(weighted);


        KeyWordCountOld privacy = new KeyWordCountOld();
        privacy.setContent("隐私保护数据挖掘,关联规则的一种扩展分类");
        privacy.setKey_words("privacy preserving data mining");
        newKeyWordCounts.add(privacy);

        KeyWordCountOld temporal = new KeyWordCountOld();
        temporal.setKey_words("temporal association rules");
        temporal.setContent("时序关联规则,关联规则的一种分类");
        newKeyWordCounts.add(temporal);

        KeyWordCountOld post_processing = new KeyWordCountOld();
        post_processing.setKey_words("post-processing");
        post_processing.setContent("关联广泛用于查找给定数据库中项目之间的关系。然而，由于生成大量规则，查找有趣的模式是一项具有挑战性的任务。传统上，此任务由后处理方法来完成，后者处理方法探索并引导用户到域的有趣规则。这些方法中的一些使用用户的知识根据用户感兴趣的定义（思考）来指导探索。然而，这个定义在进程开始之前完成。因此，用户必须知道他/她可能是什么和什么可能不感兴趣。这项工作提出了一个一般的关联规则后处理方法，在后处理阶段提取用户的知识。这样，用户不需要在数据库中具有先验知识。为此，所提出的方法在网络中建模关联规则，使用其措施来建议要由用户分类的规则，然后使用变换学习算法将这些分类传播到整个网络。因此，这种方法将后处理问题视为分类任务。进行实验以证明所提出的方法减少了用户要探索的规则的数量并且将他/她引导到域的潜在感兴趣的规则。\n");
        newKeyWordCounts.add(post_processing);

        KeyWordCountOld evolutionary = new KeyWordCountOld();
        evolutionary.setContent("将遗传用于关联规则的挖掘,关联规则挖掘方法");
        evolutionary.setKey_words("evolutionary algorithms");
//        newKeyWordCounts.add(evolutionary);

        KeyWordCountOld granular = new KeyWordCountOld();
        granular.setKey_words("granular association rule");
        granular.setContent("粒度关联规则,关联规则的一种分类???");
//        newKeyWordCounts.add(granular);

        KeyWordCountOld concept_lattice = new KeyWordCountOld();
        concept_lattice.setKey_words("concept lattice");
        concept_lattice.setContent("(基于形式概念分析的关联规则挖)概念格,基于概念格的关联规则挖掘算法,一种关联规则挖掘方法");
        newKeyWordCounts.add(concept_lattice);

        KeyWordCountOld fptree = new KeyWordCountOld();
        fptree.setKey_words("fp-tree");
        fptree.setContent("fp-tree,一种关联规则挖掘方法");
        newKeyWordCounts.add(fptree);

        KeyWordCountOld positive_negative = new KeyWordCountOld();
        positive_negative.setKey_words("positive and negative association rules");
        positive_negative.setContent("正负关联规则,一种关联规则的分类");
        newKeyWordCounts.add(positive_negative);

        KeyWordCountOld prediction = new KeyWordCountOld();
        prediction.setKey_words("prediction");
        prediction.setContent("预测,关联规则用于预测(多时间序列关联规则分析的论坛舆情趋势预测\n),一种应用方向");
        newKeyWordCounts.add(prediction);

        KeyWordCountOld web = new KeyWordCountOld();
        web.setKey_words("web usage mining");
        web.setContent("web中使用关联规则挖掘(面向Web关联规则挖掘的快速Apriori算法),关联规则的一种应用");
        newKeyWordCounts.add(web);

        KeyWordCountOld visualization = new KeyWordCountOld();
        visualization.setKey_words("visualization");
        visualization.setContent("可视化关联规则挖掘,关联规则结果可视化 ");
        newKeyWordCounts.add(visualization);

        KeyWordCountOld multiple_minimum = new KeyWordCountOld();
        multiple_minimum.setKey_words("multiple minimum supports");
        multiple_minimum.setContent("多最小支持关联规则,关联规则的一种分类");
        newKeyWordCounts.add(multiple_minimum);

        KeyWordCountOld recommender = new KeyWordCountOld();
        recommender.setKey_words("recommender system");
        recommender.setContent("推荐系统,关联规则的应用");
        newKeyWordCounts.add(recommender);

        KeyWordCountOld feature = new KeyWordCountOld();
        feature.setKey_words("feature selection");
        feature.setContent("特征选择,关联规则的一种应用");
        newKeyWordCounts.add(feature);

        KeyWordCountOld incremental = new KeyWordCountOld();
        incremental.setKey_words("incremental updating");
        incremental.setContent("增量关联规则");
        newKeyWordCounts.add(incremental);

        KeyWordCountOld xml = new KeyWordCountOld();
        xml.setKey_words("xml data mining");
        xml.setContent("基于XML和关联规则的Web挖掘研究\n首先对Web挖掘、关联规则分析及XML作了简要介绍,提出了基于XML和关联规则的Web挖掘研究思想,随后对XML结构挖掘、XML内容挖掘和基于XML的Web日志挖掘进行讨论,建立了一个较为完整的XML挖掘体系.\n");
        newKeyWordCounts.add(xml);

        KeyWordCountOld neural_network = new KeyWordCountOld();
        neural_network.setKey_words("neural network");
        neural_network.setContent("神经网络,使用神经网络挖掘关联规则");
        newKeyWordCounts.add(neural_network);

        KeyWordCountOld data_streams = new KeyWordCountOld();
        data_streams.setKey_words("data streams");
        data_streams.setContent("流式数据,一种数据结构上的关联规则挖掘");
        newKeyWordCounts.add(data_streams);

        KeyWordCountOld particle = new KeyWordCountOld();
        particle.setKey_words("particle swarm optimization");
        particle.setContent("粒子群,一种基于粒子群优化的关联规则方法,一种关联规则挖掘方法");
        newKeyWordCounts.add(particle);

        KeyWordCountOld linguistic = new KeyWordCountOld();
        linguistic.setKey_words("linguistic terms");
        linguistic.setContent("语言值关联规则(关联规则的数量型的一种研究方法)");
        newKeyWordCounts.add(linguistic);

        KeyWordCountOld retrieval = new KeyWordCountOld();
        retrieval.setKey_words("information retrieval");
        retrieval.setContent("信息检索,(关联规则挖掘在网络信息检索中的应用),关联规则的一种应用");
        newKeyWordCounts.add(retrieval);

        KeyWordCountOld redundancy = new KeyWordCountOld();
        redundancy.setKey_words("redundancy");
        redundancy.setContent("冗余关联规则,一般关联规则挖掘包含冗余知识,需要去除");
//        newKeyWordCounts.add(redundancy);

        KeyWordCountOld domain_knowledge = new KeyWordCountOld();
        domain_knowledge.setKey_words("domain knowledge");
        domain_knowledge.setContent("基于领域知识指导的医学图像关联规则挖掘,关联规则的一种研究方法");
//        newKeyWordCounts.add(domain_knowledge);

        KeyWordCountOld e_commerce = new KeyWordCountOld();
        e_commerce.setKey_words("e-commerce");
        e_commerce.setContent("电子商务,关联规则的应用方向");
//        newKeyWordCounts.add(e_commerce);

        KeyWordCountOld binary = new KeyWordCountOld();
        binary.setKey_words("binary");
        binary.setContent("二进制,(基于二进制的关联规则挖掘算法),关联规则的一种研究方法");
        newKeyWordCounts.add(binary);

        KeyWordCountOld svm = new KeyWordCountOld();
        svm.setKey_words("svm");
        svm.setContent("基于SVM与关联规则中医舌象数据挖掘技术初步研究,将关联规则和svm结合使用");
        newKeyWordCounts.add(svm);
        for (KeyWordCountOld keyWordCount : keyWordCounts) {
            switch (keyWordCount.getKey_words()) {
                case "fuzzy association rules":
                case "fuzzy sets":
                case "fuzzy logic":
                case "fuzzy set":
                case "fuzzy association rule":
                case "fuzzy data mining":
                case "fuzzy association rule mining":
                case "fuzzy":
                    fuzzy.setCount(fuzzy.getCount() + keyWordCount.getCount());
                    break;
                case "classification":
                case "associative classification":
                case "classification rules":
                case "classification association rules":
                case "texture classification":
                case "classification method":
                    associativeClassification.setCount(associativeClassification.getCount() + keyWordCount.getCount());
                    break;
                case "clustering":
                case "document clustering":
                case "hierarchical clustering":
                    clustering.setCount(clustering.getCount() + keyWordCount.getCount());
                    break;
                case "genetic algorithm":
                case "genetic algorithms":
                case "gene ontology":
                case "genetic network programming":
                case "multi-objective genetic algorithms":
                case "genetic programming":
                case "genetic fuzzy systems":
                case "grammar guided genetic programming":
                case "grammar-guided genetic programming":
                    genetic.setCount(genetic.getCount() + keyWordCount.getCount());
                    break;
                case "quantitative association rules":
                case "quantitative attributes":
                case "quantitative value":
                case "quantitative association rule":
                case "quantitative data":
                    quantitative.setCount(quantitative.getCount() + keyWordCount.getCount());
                    break;
                case "interestingness measures":
                case "interestingness":
                case "interestingness measure":
                case "interesting rules":
                case "interest":
                case "interest association rules":
                case "objective interestingness":
                    interestingness.setCount(interestingness.getCount() + keyWordCount.getCount());
                    break;
                case "distributed data mining":
                case "distributed databases":
                case "distributed database":
                case "distributed algorithms":
                case "distributed computing":
                case "distributed system":
                case "distributed":
                case "distributed mining":
                case "distributed computation":
                case "grid computing":
                    distributed.setCount(distributed.getCount() + keyWordCount.getCount());
                    break;
                case "parallel computing":
                case "parallel algorithm":
                case "parallel algorithms":
                case "parallel":
                    parallel.setCount(parallel.getCount() + keyWordCount.getCount());
                    break;
                case "generalized association rules":
                case "generalized association rule":
                case "generalized association rule mining":
                case "generalized implications":
                case "generalized rule induction":
                    generalized.setCount(generalized.getCount() + keyWordCount.getCount());
                    break;
                case "intrusion detection":
                case "anomaly detection":
                case "misuse detection":
                case "change detection":
                case "outlier detection":
                    intrusion.setCount(intrusion.getCount() + keyWordCount.getCount());
                    break;
                case "text mining":
                case "text retrieval":
                    text.setCount(text.getCount() + keyWordCount.getCount());
                    break;
                case "rough set":
                case "rough sets":
                case "rough set theory":
                    rough.setCount(rough.getCount() + keyWordCount.getCount());
                    break;
                case "spatial data mining":
                case "spatial association rules":
                case "spatial association rule":
                case "spatial data":
                case "spatial association rules mining":
                    spatial.setCount(spatial.getCount() + keyWordCount.getCount());
                    break;
                case "weighted association rules":
                case "weighted support":
                case "weighted rules":
                case "weighted mining":
                case "weighted":
                case "weighted association rule":
                    weighted.setCount(weighted.getCount() + keyWordCount.getCount());
                    break;
                case "privacy preserving data mining":
                case "privacy preserving":
                case "privacy":
                case "privacy preservation":
                case "privacy protection":
                case "privacy-preserving":
                    privacy.setCount(privacy.getCount() + keyWordCount.getCount());
                    break;
                case "temporal association rules":
                case "temporal data mining":
                case "temporal mining":
                case "temporal association rule":
                    temporal.setCount(temporal.getCount() + keyWordCount.getCount());
                    break;
                case "post-processing":
                    post_processing.setCount(post_processing.getCount() + keyWordCount.getCount());
                    break;
                case "evolutionary algorithms":
                case "evolutionary computation":
                case "multi-objective evolutionary algorithm":
                case "multi-objective evolutionary algorithms":
                    evolutionary.setCount(evolutionary.getCount() + keyWordCount.getCount());
                    break;
                case "granular computing":
                case "granular association rule":
                    granular.setCount(granular.getCount() + keyWordCount.getCount());
                    break;
                case "concept lattice":
                case "formal concept analysis":
                case "concept hierarchy":
                case "lattices":
                case "concept":
                    concept_lattice.setCount(concept_lattice.getCount() + keyWordCount.getCount());
                    break;
                case "fp-tree":
                case "fp-growth":
                case "fp-forest":
                    fptree.setCount(fptree.getCount() + keyWordCount.getCount());
                    break;
                case "negative association rules":
                case "positive and negative association rules":
                case "negative association rule":
                case "negative border":
                    positive_negative.setCount(positive_negative.getCount() + keyWordCount.getCount());
                    break;
                case "prediction":
                case "time series":
                    prediction.setCount(prediction.getCount() + keyWordCount.getCount());
                    break;
                case "web usage mining":
                case "web mining":
                case "web data mining":
                case "web association rules":
                case "web":
                case "web image retrieval":
                    web.setCount(web.getCount() + keyWordCount.getCount());
                    break;
                case "visualization":
                case "information visualization":
                    visualization.setCount(visualization.getCount() + keyWordCount.getCount());
                    break;
                case "multiple minimum supports":
                case "multiple minimum support":
                case "multiple minimum supports and taxonomy":
                    multiple_minimum.setCount(multiple_minimum.getCount() + keyWordCount.getCount());
                    break;
                case "recommender system":
                case "recommender systems":
                case "collaborative filtering":
                case "hybrid recommender system":
                    recommender.setCount(recommender.getCount() + keyWordCount.getCount());
                    break;
                case "feature selection":
                case "feature":
                case "features":
                    feature.setCount(feature.getCount() + keyWordCount.getCount());
                    break;
                case "incremental updating":
                case "incremental mining":
                case "incremental dataset":
                case "incremental update":
                case "incremental maintenance":
                    incremental.setCount(incremental.getCount() + keyWordCount.getCount());
                    break;
                case "xml data mining":
                case "xml mining":
                case "xml":
                    xml.setCount(xml.getCount() + keyWordCount.getCount());
                    break;
                case "neural network":
                case "neural networks":
                    neural_network.setCount(neural_network.getCount() + keyWordCount.getCount());
                    break;
                case "data streams":
                    data_streams.setCount(data_streams.getCount() + keyWordCount.getCount());
                    break;
                case "particle swarm optimization":
                case "particle swam optimization":
                    particle.setCount(particle.getCount() + keyWordCount.getCount());
                    break;
                case "linguistic terms":
                    linguistic.setCount(linguistic.getCount() + keyWordCount.getCount());
                    break;
                case "information retrieval":
                case "content-based image retrieval":
                case "image retrieval":
                    retrieval.setCount(retrieval.getCount() + keyWordCount.getCount());
                    break;
                case "redundancy":
                    redundancy.setCount(redundancy.getCount() + keyWordCount.getCount());
                    break;
                case "e-commerce":
                    e_commerce.setCount(e_commerce.getCount() + keyWordCount.getCount());
                    break;
                case "binary":
                    binary.setCount(binary.getCount() + keyWordCount.getCount());
                    break;
                case "domain knowledge":
                    domain_knowledge.setCount(domain_knowledge.getCount() + keyWordCount.getCount());
                    break;
                case "svm":
                case "support vector machines":
                case "support vector machine":
                    svm.setCount(svm.getCount() + keyWordCount.getCount());
                    break;
                case "fuzzy clustering":
                    fuzzy.setCount(fuzzy.getCount() + keyWordCount.getCount());
                    clustering.setCount(clustering.getCount() + keyWordCount.getCount());
                    break;

            }
        }
        Collections.sort(newKeyWordCounts, new Comparator<KeyWordCountOld>() {
            @Override
            public int compare(KeyWordCountOld o1, KeyWordCountOld o2) {
                return o1.getCount() - o2.getCount();
            }
        });
        return newKeyWordCounts;
    }

    public static String inString(String[] strs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            stringBuilder.append("'" + strs[i] + "'");
            if (i != strs.length - 1) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }
}
