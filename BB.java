package ha;

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


public class BB {
public static void main(String[] args) throws Exception {
	Configuration c = new Configuration();
	String files[] = new GenericOptionsParser(c, args).getRemainingArgs();
	Path input = new Path(files[0]);
	Path output = new Path(files[1]);
	Job j = new Job(c,"word");
	j.setJarByClass(BB.class);
	j.setMapperClass(Map.class);
	j.setReducerClass(Reduce.class);
	j.setOutputKeyClass(Text.class);
	j.setOutputValueClass(IntWritable.class);
	FileInputFormat.addInputPath(j,input);
	FileOutputFormat.setOutputPath(j,output);
	System.exit(j.waitForCompletion(true)?0:1);
}
static public class Map extends Mapper<LongWritable,Text,Text,IntWritable>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String words[] = line.split(",");
		for (String word : words) {
			Text outputkey = new Text(word.toUpperCase().trim());
			IntWritable outputvalue = new IntWritable(1);
			context.write(outputkey, outputvalue);
		}
	}
}
static public class Reduce extends Reducer<Text, IntWritable, Text, IntWritable>{
	@Override
	protected void reduce(Text arg0, Iterable<IntWritable> arg1,
			Reducer<Text, IntWritable, Text, IntWritable>.Context arg2) throws IOException, InterruptedException {
		int sum=0;
		for(IntWritable value:arg1) {
			sum +=value.get();
		}
		arg2.write(arg0, new IntWritable(sum));
	}
}
}

