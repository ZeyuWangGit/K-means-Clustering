
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import nlp.nicta.filters.StopWordChecker;
import text.WordCount;
import util.DocUtils;

public class SingleDocHandle {

	public StopWordChecker _swc = new StopWordChecker();
	public TreeSet<WordCount> _wordCounts;
	public HashMap<String,Integer> _topWord2Index;
	public HashMap<String,Integer> DocTermCount;
	
	public SingleDocHandle(File files, int num_top_words, boolean ignore_stop_words) {
		HashMap<String,WordCount> word2count = new HashMap<String,WordCount>();		
		String file_content = DocUtils.ReadFile(files);
		Map<Object,Double> word_counts = DocUtils.ConvertToFeatureMap(file_content);
		for (Map.Entry<Object, Double> me : word_counts.entrySet()) {
			String key = (String)me.getKey();
			WordCount wc = word2count.get(key);
			if (wc == null) {
				wc = new WordCount(key, (int)me.getValue().doubleValue());
				word2count.put(key, wc);
			} else
				wc._count++;
		}
		
		_wordCounts = new TreeSet<WordCount>(word2count.values());
		_topWord2Index = new HashMap<String,Integer>();
		DocTermCount = new HashMap<String,Integer>();
		int index = 0;
		for (WordCount wc : _wordCounts) {
			if (ignore_stop_words && _swc.isStopWord(wc._word))
				continue;
			_topWord2Index.put(wc._word, index);
			DocTermCount.put(wc._word, wc._count);
			if (++index >= num_top_words)
				break;
		}
	}
	public Map<String,Integer> getDocTermCount() {
		return DocTermCount;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//SingleDocHandle UB = new SingleDocHandle("data/two_newsgroups/" /* data source */, 
			//	/* num top words */100, /* remove stopwords */true);
	}

}
