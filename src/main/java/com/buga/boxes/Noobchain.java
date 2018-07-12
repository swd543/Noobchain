package com.buga.boxes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buga.boxes.to.Block;
import com.buga.boxes.to.DataTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Noobchain {
	private static final Logger LOGGER=LoggerFactory.getLogger(Noobchain.class);
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		LOGGER.info("Started!");
		StringBuffer buffer=new StringBuffer("0");
		List<DataTO> data=List.of(new DataTO("Hello world!"), new DataTO("My"), new DataTO("name"), new DataTO("is"), new DataTO("Swapneel"));
		List<Block<DataTO>>blockchain=data.stream().map(d->{
			Block<DataTO> block=null;
			try {
				block = new Block<>(buffer.toString(), d);
			} catch (NoSuchAlgorithmException | IOException e) {
				throw new RuntimeException(e);
			}
			buffer.setLength(0);
			buffer.insert(0, block.getHash());
			return block;
		}).collect(Collectors.toList());
		prettifyObject(blockchain);
		printIfBlockChainValid(blockchain);
		blockchain.get(2).getObject().setName("I was hacked!");
		printIfBlockChainValid(blockchain);
	}
	
	private static final <T> void printIfBlockChainValid(List<Block<T>> blockchain) throws NoSuchAlgorithmException, IOException {
		LOGGER.info("Is blockchain valid? "+isBlockChainValid(blockchain));
	}
	
	private static final <T> boolean isBlockChainValid(List<Block<T>> blockchain) throws NoSuchAlgorithmException, IOException {
		for(int i=0;i<blockchain.size()-1;i++) {
			if(!isBlockValid(blockchain.get(i+1), blockchain.get(i)))
				return false;
		}
		return true;
	}
	
	private static final <T> boolean isBlockValid(Block<T> current, Block<T> previous) throws NoSuchAlgorithmException, IOException {
		if(!current.calculateHash().equals(current.getHash())) {
			LOGGER.warn("Current hashes aint equal. Calculated hash : "+current.calculateHash());
			prettifyObject(current);
			prettifyObject(previous);
			return false;
		}
		if(!previous.getHash().equals(current.getPreviousHash())) {
			LOGGER.warn("Previous hash aint equal.");
			prettifyObject(current);
			prettifyObject(previous);
			return false;
		}
		return true;
	}
	
	private static final <T> void prettifyObject(T object) throws JsonProcessingException{
		LOGGER.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object));
	}
}
