package backend.chunk;

import java.util.ArrayList;

import org.springframework.batch.item.ItemProcessor;

public class ChunkProcessor implements ItemProcessor<ArrayList<String>, String> {
	@Override
	public String process(ArrayList<String> data) throws Exception {
		return data.get(1);
	}
}
