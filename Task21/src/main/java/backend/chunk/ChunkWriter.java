package backend.chunk;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class ChunkWriter implements ItemWriter<String>{
	@Override
	public void write(List<? extends String> datas) throws Exception {
		for (String data : datas) {
			System.out.println(data+"\n");
		}
	}
}
