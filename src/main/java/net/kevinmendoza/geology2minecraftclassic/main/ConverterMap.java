package net.kevinmendoza.geology2minecraftclassic.main;

import java.util.HashMap;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.kevinmendoza.geology2minecraftclassic.blockconversion.blockdata.CompositionMapFactory;
import net.kevinmendoza.geology2minecraftclassic.configuration.ConfigBind;
import net.kevinmendoza.geoworldlibrary.utilities.IBlockStateCreator;

class ConverterMap {
	
	private CompositionMapFactory factory;
	private HashMap<Long,IBlockStateCreator> creatorMap;
	
	public ConverterMap() {
		Injector injector = Guice.createInjector(new ConfigBind());
		factory = injector.getInstance(CompositionMapFactory.class);
		creatorMap=new HashMap<>();
	}

	public IBlockStateCreator getCreator(long seed,boolean newInstance) {
		if(newInstance) {
			return factory.createConverter();
		}
		else {
			return returnMapValue(seed);
		}
	}


	private  IBlockStateCreator returnMapValue(long seed) {
		if(creatorMap.containsKey(seed)) {
			return creatorMap.get(seed);
		}
		else {
			return createNewInstance(seed);
		}
	}

	private  IBlockStateCreator createNewInstance(long seed) {
		IBlockStateCreator geology = factory.createConverter();
		creatorMap.put(seed, geology);
		return geology;
	}

}
