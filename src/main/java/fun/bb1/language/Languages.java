package fun.bb1.language;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fun.bb1.registry.IRegistry;
import fun.bb1.registry.SimpleRegistry;

/**
 * 
 * Copyright 2022 BradBot_1
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * A collection of methods to make cross language stuff easier
 * 
 * @author BradBot_1
 */
public final class Languages {
	
	private static final @NotNull Map<String, IRegistry<String, String>> LANGUAGES = new ConcurrentHashMap<String, IRegistry<String,String>>();
	/**
	 * Provides an {@link Function} to facilitate dynamic language output
	 * 
	 * @param key The key to be translated
	 * @return A {@link Function} that will get the language string for the provided language
	 */
	public static final @NotNull Function<String, @Nullable String> convert(@NotNull final String key) {
		return (lang) -> getLanguage(lang).get(key);
	}
	/**
	 * Translates the provided key into the required language
	 * 
	 * @apiNote This is the same as calling {@link #convert(String)}.apply(lang) but gives a null safe return
	 * 
	 * @param key The key to be translated
	 * @param lang The language to translate to
	 * @return The translated string
	 */
	public static final @NotNull String convert(@NotNull final String key, @NotNull final String lang) {
		final String given = convert(key).apply(lang);
		return given == null ? key : given;
	}
	/**
	 * Gets and returns an {@link IRegistry} for the requested language
	 * 
	 * @apiNote If the desired {@link IRegistry} doesn't exist it will be created
	 * 
	 * @param key The language that the repository pertains to
	 * @return An {@link IRegistry} for the requested language
	 */
	public static final @NotNull IRegistry<String, String> getLanguage(@NotNull final String key) {
		if (LANGUAGES.containsKey(key)) LANGUAGES.put(key, new SimpleRegistry<String, String>());
		return LANGUAGES.get(key);
	}
	
	private Languages() { }
	
}
