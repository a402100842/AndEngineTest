package org.anddev.andengine.test.entity.layer.tiled.tmx;

import java.io.ByteArrayInputStream;

import org.anddev.andengine.entity.layer.tiled.tmx.TMXLoader;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTiledMap;
import org.anddev.andengine.entity.layer.tiled.tmx.util.constants.TMXConstants;
import org.anddev.andengine.opengl.texture.TextureManager;

import android.test.AndroidTestCase;

/**
 * @author Nicolas Gramlich
 * @since 19:10:39 - 20.07.2010
 */
public class TMXLoaderTest extends AndroidTestCase implements TMXConstants {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String TESTMAP = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		+ "<map version=\"1.0\" orientation=\"orthogonal\" width=\"10\" height=\"10\" tilewidth=\"32\" tileheight=\"32\">"
		+ " <tileset firstgid=\"1\" name=\"TestTileSet 1\" tilewidth=\"32\" tileheight=\"32\">"
		+ "  <image source=\"../test/test_file_1.png\"/>"
		+ " </tileset>"
		+ " <tileset firstgid=\"3\" name=\"TestTileSet 2\" tilewidth=\"32\" tileheight=\"32\">"
		+ "  <image source=\"../test/test_file_2.png\"/>"
		+ " </tileset>"
		+ " <layer name=\"TestLayer 1\" width=\"10\" height=\"10\">"
		+ "  <data encoding=\"base64\" compression=\"gzip\">"
		+ "   H4sIAAAAAAAAC2NgYGBgJBITAsjqmHBgbOrQAT511BYjxl5i/IEPEBu+IAwAOtajNpABAAA="
		+ "  </data>"
		+ " </layer>"
		+ " <layer name=\"TestLayer 2\" width=\"10\" height=\"10\" visible=\"0\">"
		+ "  <data encoding=\"base64\" compression=\"gzip\">"
		+ "   H4sIAAAAAAAAC2NhIB6wEMDI6vCZgc7GZg4uddQWI8ZebABdHTHhQgwAAJzp8geQAQAAA"
		+ "  </data>"
		+ " </layer>"
		+ "</map>";

	// ===========================================================
	// Fields
	// ===========================================================

	private TMXLoader mTMXLoader;

	// ===========================================================
	// Constructors
	// ===========================================================

	@Override
	protected void setUp() throws Exception {
		this.mTMXLoader = new TMXLoader(this.getContext(), new TextureManager());
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void testLoad() throws Exception {
		final TMXTiledMap tmxTiledMap = this.mTMXLoader.load(new ByteArrayInputStream(TESTMAP.getBytes("UTF-8")));
		
		assertEquals(TAG_MAP_ATTRIBUTE_ORIENTATION_VALUE_ORTHOGONAL, tmxTiledMap.getOrientation());
		assertEquals(10, tmxTiledMap.getWidth());
		assertEquals(10, tmxTiledMap.getHeight());
		assertEquals(32, tmxTiledMap.getTileWidth());
		assertEquals(32, tmxTiledMap.getTileHeight());

		{
			assertEquals(2, tmxTiledMap.getTMXTileSets().size());

			assertEquals(1, tmxTiledMap.getTMXTileSets().get(0).getFirstGlobalTileID());
			assertEquals("TestTileSet 1", tmxTiledMap.getTMXTileSets().get(0).getName());
			assertEquals(32, tmxTiledMap.getTMXTileSets().get(0).getTileWidth());
			assertEquals(32, tmxTiledMap.getTMXTileSets().get(0).getTileHeight());
			assertEquals("../test/test_file_1.png", tmxTiledMap.getTMXTileSets().get(0).getImageSource());

			assertEquals(3, tmxTiledMap.getTMXTileSets().get(1).getFirstGlobalTileID());
			assertEquals("TestTileSet 2", tmxTiledMap.getTMXTileSets().get(1).getName());
			assertEquals(32, tmxTiledMap.getTMXTileSets().get(1).getTileWidth());
			assertEquals(32, tmxTiledMap.getTMXTileSets().get(1).getTileHeight());
			assertEquals("../test/test_file_2.png", tmxTiledMap.getTMXTileSets().get(1).getImageSource());
		}
		
		{
			assertEquals(2, tmxTiledMap.getTMXLayers().size());

			assertEquals("TestLayer 1", tmxTiledMap.getTMXLayers().get(0).getName());
			assertEquals(10, tmxTiledMap.getTMXLayers().get(0).getTileColumns());
			assertEquals(10, tmxTiledMap.getTMXLayers().get(0).getTileRows());

			assertEquals("TestLayer 2", tmxTiledMap.getTMXLayers().get(1).getName());
			assertEquals(10, tmxTiledMap.getTMXLayers().get(1).getTileColumns());
			assertEquals(10, tmxTiledMap.getTMXLayers().get(1).getTileRows());
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
