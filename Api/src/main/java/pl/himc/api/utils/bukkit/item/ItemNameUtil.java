package pl.himc.api.utils.bukkit.item;

import org.bukkit.Material;

import java.util.HashMap;

public final class ItemNameUtil {

    private static HashMap<Material, String> polishNames = new HashMap<>();
    
    public ItemNameUtil() {
        this.polishNames.put(Material.AIR, "Reka");
        this.polishNames.put(Material.STONE, "Kamien");
        this.polishNames.put(Material.GRASS, "Bloki trawy");
        this.polishNames.put(Material.DIRT, "Ziemia");
        this.polishNames.put(Material.COBBLESTONE, "Bruk");
        this.polishNames.put(Material.WOOD, "Deski");
        this.polishNames.put(Material.SAPLING, "Sadzonka");
        this.polishNames.put(Material.BEDROCK, "Bedrock");
        this.polishNames.put(Material.WATER, "Woda");
        this.polishNames.put(Material.STATIONARY_WATER, "Woda");
        this.polishNames.put(Material.LAVA, "Lawa");
        this.polishNames.put(Material.STATIONARY_LAVA, "Lawa");
        this.polishNames.put(Material.SAND, "Piasek");
        this.polishNames.put(Material.GRAVEL, "Zwir");
        this.polishNames.put(Material.GOLD_ORE, "Ruda zlota");
        this.polishNames.put(Material.IRON_ORE, "Ruda zelaza");
        this.polishNames.put(Material.COAL_ORE, "Ruda wegla");
        this.polishNames.put(Material.LOG, "Drewno");
        this.polishNames.put(Material.LEAVES, "Liscie");
        this.polishNames.put(Material.SPONGE, "Gabka");
        this.polishNames.put(Material.GLASS, "Szklo");
        this.polishNames.put(Material.LAPIS_ORE, "Ruda lipisu");
        this.polishNames.put(Material.LAPIS_BLOCK, "Blok lapisu");
        this.polishNames.put(Material.DISPENSER, "Dozownik");
        this.polishNames.put(Material.SANDSTONE, "Pisakowiec");
        this.polishNames.put(Material.NOTE_BLOCK, "Note block");
        this.polishNames.put(Material.BED_BLOCK, "Lozko");
        this.polishNames.put(Material.POWERED_RAIL, "Zasilane tory");
        this.polishNames.put(Material.DETECTOR_RAIL, "Tory z czujnikiem");
        this.polishNames.put(Material.PISTON_STICKY_BASE, "Tlok");
        this.polishNames.put(Material.WEB, "Pajeczyna");
        this.polishNames.put(Material.LONG_GRASS, "Trawa");
        this.polishNames.put(Material.DEAD_BUSH, "Uschneity krzak");
        this.polishNames.put(Material.PISTON_BASE, "Tlok");
        this.polishNames.put(Material.PISTON_EXTENSION, "Tlok");
        this.polishNames.put(Material.WOOL, "Welna");
        this.polishNames.put(Material.PISTON_MOVING_PIECE, "Tlok");
        this.polishNames.put(Material.YELLOW_FLOWER, "Tulipan");
        this.polishNames.put(Material.RED_ROSE, "Roza");
        this.polishNames.put(Material.BROWN_MUSHROOM, "Brazowy grzyb");
        this.polishNames.put(Material.RED_MUSHROOM, "Muchomor");
        this.polishNames.put(Material.GOLD_BLOCK, "Blok zlota");
        this.polishNames.put(Material.IRON_BLOCK, "Blok zelaza");
        this.polishNames.put(Material.DOUBLE_STEP, "Podwojna polplytka");
        this.polishNames.put(Material.STEP, "Polplytka");
        this.polishNames.put(Material.BRICK, "Cegly");
        this.polishNames.put(Material.TNT, "Tnt");
        this.polishNames.put(Material.BOOKSHELF, "Biblioteczka");
        this.polishNames.put(Material.MOSSY_COBBLESTONE, "Zamszony bruk");
        this.polishNames.put(Material.OBSIDIAN, "Obsydian");
        this.polishNames.put(Material.TORCH, "Pochodnia");
        this.polishNames.put(Material.FIRE, "Ogien");
        this.polishNames.put(Material.MOB_SPAWNER, "Mob spawner");
        this.polishNames.put(Material.WOOD_STAIRS, "Drewniane schodki");
        this.polishNames.put(Material.CHEST, "Skrzynia");
        this.polishNames.put(Material.REDSTONE_WIRE, "Redstone");
        this.polishNames.put(Material.DIAMOND_ORE, "Ruda diamentu");
        this.polishNames.put(Material.DIAMOND_BLOCK, "Blok diamentu");
        this.polishNames.put(Material.WORKBENCH, "Stol rzemieslniczy");
        this.polishNames.put(Material.CROPS, "Nasionka");
        this.polishNames.put(Material.SOIL, "Nasionka");
        this.polishNames.put(Material.FURNACE, "Piecyk");
        this.polishNames.put(Material.BURNING_FURNACE, "Piecyk");
        this.polishNames.put(Material.SIGN_POST, "Tabliczka");
        this.polishNames.put(Material.WOODEN_DOOR, "Drewniane drzwi");
        this.polishNames.put(Material.LADDER, "Drabinka");
        this.polishNames.put(Material.RAILS, "Tory");
        this.polishNames.put(Material.COBBLESTONE_STAIRS, "brukowe schody");
        this.polishNames.put(Material.WALL_SIGN, "Tabliczka");
        this.polishNames.put(Material.LEVER, "Dzwignia");
        this.polishNames.put(Material.STONE_PLATE, "Plytka naciskowa");
        this.polishNames.put(Material.IRON_DOOR_BLOCK, "Zelazne drzwi");
        this.polishNames.put(Material.WOOD_PLATE, "Plytka nasickowa");
        this.polishNames.put(Material.REDSTONE_ORE, "Ruda redstone");
        this.polishNames.put(Material.GLOWING_REDSTONE_ORE, "Ruda redstone");
        this.polishNames.put(Material.REDSTONE_TORCH_OFF, "Czerwona pochodnia");
        this.polishNames.put(Material.REDSTONE_TORCH_ON, "Czerwona pochodnia");
        this.polishNames.put(Material.STONE_BUTTON, "Kamienny przycisk");
        this.polishNames.put(Material.SNOW, "Snieg");
        this.polishNames.put(Material.ICE, "Lod");
        this.polishNames.put(Material.SNOW_BLOCK, "Snieg");
        this.polishNames.put(Material.CACTUS, "Kaktus");
        this.polishNames.put(Material.CLAY, "Glina");
        this.polishNames.put(Material.SUGAR_CANE_BLOCK, "Trzcina");
        this.polishNames.put(Material.JUKEBOX, "Szafa grajaca");
        this.polishNames.put(Material.FENCE, "Plotek");
        this.polishNames.put(Material.PUMPKIN, "Dynia");
        this.polishNames.put(Material.NETHERRACK, "Netherrack");
        this.polishNames.put(Material.SOUL_SAND, "Pisaek dusz");
        this.polishNames.put(Material.GLOWSTONE, "Jasnoglaz");
        this.polishNames.put(Material.PORTAL, "Portal");
        this.polishNames.put(Material.JACK_O_LANTERN, "Jack'o'latern");
        this.polishNames.put(Material.CAKE_BLOCK, "Ciasto");
        this.polishNames.put(Material.DIODE_BLOCK_OFF, "Przekaznik");
        this.polishNames.put(Material.DIODE_BLOCK_ON, "Przekaznik");
        this.polishNames.put(Material.STAINED_GLASS, "Utwardzone szklo");
        this.polishNames.put(Material.TRAP_DOOR, "Wlaz");
        this.polishNames.put(Material.MONSTER_EGGS, "Jajko potwora");
        this.polishNames.put(Material.SMOOTH_BRICK, "Cegly");
        this.polishNames.put(Material.HUGE_MUSHROOM_1, "Duzy grzyb");
        this.polishNames.put(Material.HUGE_MUSHROOM_2, "Duzy grzyb");
        this.polishNames.put(Material.IRON_FENCE, "Kraty");
        this.polishNames.put(Material.THIN_GLASS, "Szyba");
        this.polishNames.put(Material.MELON_BLOCK, "Arbuz");
        this.polishNames.put(Material.PUMPKIN_STEM, "Dynia");
        this.polishNames.put(Material.MELON_STEM, "Arbuz");
        this.polishNames.put(Material.VINE, "Pnacze");
        this.polishNames.put(Material.FENCE_GATE, "Furtka");
        this.polishNames.put(Material.BRICK_STAIRS, "Ceglane schodki");
        this.polishNames.put(Material.SMOOTH_STAIRS, "Kamienne schodki");
        this.polishNames.put(Material.MYCEL, "Grzybnia");
        this.polishNames.put(Material.WATER_LILY, "Lilia wodna");
        this.polishNames.put(Material.NETHER_BRICK, "Cegly netherowe");
        this.polishNames.put(Material.NETHER_FENCE, "Netherowy plotek");
        this.polishNames.put(Material.NETHER_BRICK_STAIRS, "Netherowe schodki");
        this.polishNames.put(Material.NETHER_WARTS, "Brodawki");
        this.polishNames.put(Material.ENCHANTMENT_TABLE, "Stol do enchantu");
        this.polishNames.put(Material.BREWING_STAND, "Stol alchemiczny");
        this.polishNames.put(Material.CAULDRON, "Kociol");
        this.polishNames.put(Material.ENDER_PORTAL, "Ender portal");
        this.polishNames.put(Material.ENDER_PORTAL_FRAME, "Ender portal");
        this.polishNames.put(Material.ENDER_STONE, "Kamien kresu");
        this.polishNames.put(Material.DRAGON_EGG, "Jajko smoka");
        this.polishNames.put(Material.REDSTONE_LAMP_OFF, "Lampa");
        this.polishNames.put(Material.REDSTONE_LAMP_ON, "Lampa");
        this.polishNames.put(Material.WOOD_DOUBLE_STEP, "Podwojna drewniana polplytka");
        this.polishNames.put(Material.WOOD_STEP, "Drewnania polplytka");
        this.polishNames.put(Material.COCOA, "Kakao");
        this.polishNames.put(Material.SANDSTONE_STAIRS, "Piaskowe schodki");
        this.polishNames.put(Material.EMERALD_ORE, "Ruda szmaragdu");
        this.polishNames.put(Material.ENDER_CHEST, "Skrzynia kresu");
        this.polishNames.put(Material.TRIPWIRE_HOOK, "Potykacz");
        this.polishNames.put(Material.TRIPWIRE, "Potykacz");
        this.polishNames.put(Material.EMERALD_BLOCK, "Blok szmaragdu");
        this.polishNames.put(Material.SPRUCE_WOOD_STAIRS, "Drewniane schodki");
        this.polishNames.put(Material.BIRCH_WOOD_STAIRS, "Drewniane schodki");
        this.polishNames.put(Material.JUNGLE_WOOD_STAIRS, "Drewniane schodki");
        this.polishNames.put(Material.COMMAND, "Blok polecen");
        this.polishNames.put(Material.BEACON, "Magiczna latarnia");
        this.polishNames.put(Material.COBBLE_WALL, "Brukowy plotek");
        this.polishNames.put(Material.FLOWER_POT, "Doniczka");
        this.polishNames.put(Material.CARROT, "Marchewka");
        this.polishNames.put(Material.POTATO, "Ziemniak");
        this.polishNames.put(Material.WOOD_BUTTON, "drewniany przycisk");
        this.polishNames.put(Material.SKULL, "Glowa");
        this.polishNames.put(Material.ANVIL, "Kowadlo");
        this.polishNames.put(Material.TRAPPED_CHEST, "Skrzynka z pulapka");
        this.polishNames.put(Material.GOLD_PLATE, "Zlota polplytka");
        this.polishNames.put(Material.IRON_PLATE, "Zelaza polplytka");
        this.polishNames.put(Material.REDSTONE_COMPARATOR_OFF, "Komparator");
        this.polishNames.put(Material.REDSTONE_COMPARATOR_ON, "Komparator");
        this.polishNames.put(Material.DAYLIGHT_DETECTOR, "Detektor swiatla dziennego");
        this.polishNames.put(Material.REDSTONE_BLOCK, "Blok redstone");
        this.polishNames.put(Material.QUARTZ_ORE, "Ruda kwarcu");
        this.polishNames.put(Material.HOPPER, "Lej");
        this.polishNames.put(Material.QUARTZ_BLOCK, "Blok kwarcu");
        this.polishNames.put(Material.QUARTZ_STAIRS, "Lwarcowe schodki");
        this.polishNames.put(Material.ACTIVATOR_RAIL, "Tory aktywacyjne");
        this.polishNames.put(Material.DROPPER, "Podajnik");
        this.polishNames.put(Material.STAINED_CLAY, "Utwardzona glina");
        this.polishNames.put(Material.STAINED_GLASS_PANE, "Utwardzona szyba");
        this.polishNames.put(Material.LEAVES_2, "Liscie");
        this.polishNames.put(Material.LOG_2, "Drewno");
        this.polishNames.put(Material.ACACIA_STAIRS, "Drewniane schodki");
        this.polishNames.put(Material.DARK_OAK_STAIRS, "Drewniane schodki");
        this.polishNames.put(Material.HAY_BLOCK, "Sloma");
        this.polishNames.put(Material.CARPET, "Dywan");
        this.polishNames.put(Material.HARD_CLAY, "Glina");
        this.polishNames.put(Material.COAL_BLOCK, "Blok wegla");
        this.polishNames.put(Material.PACKED_ICE, "Utwardzony lod");
        this.polishNames.put(Material.DOUBLE_PLANT, "Sadzonka");
        this.polishNames.put(Material.IRON_SPADE, "Zelazna lopata");
        this.polishNames.put(Material.IRON_PICKAXE, "Zelazny kilof");
        this.polishNames.put(Material.IRON_AXE, "Zelazna siekiera");
        this.polishNames.put(Material.FLINT_AND_STEEL, "Zapalniczka");
        this.polishNames.put(Material.APPLE, "Jablko");
        this.polishNames.put(Material.BOW, "Luk");
        this.polishNames.put(Material.ARROW, "Strzala");
        this.polishNames.put(Material.COAL, "Wegiel");
        this.polishNames.put(Material.DIAMOND, "Diament");
        this.polishNames.put(Material.IRON_INGOT, "Sztabka zelaza");
        this.polishNames.put(Material.GOLD_INGOT, "Sztabka zlota");
        this.polishNames.put(Material.IRON_SWORD, "Zelazny miecz");
        this.polishNames.put(Material.WOOD_SWORD, "Drewniany miecz");
        this.polishNames.put(Material.WOOD_SPADE, "Drewniana lopata");
        this.polishNames.put(Material.WOOD_PICKAXE, "Drewniany kilof");
        this.polishNames.put(Material.WOOD_AXE, "Drewnania siekiera");
        this.polishNames.put(Material.STONE_SWORD, "Kamienny miecz");
        this.polishNames.put(Material.STONE_SPADE, "Kamienna lopata");
        this.polishNames.put(Material.STONE_PICKAXE, "Kamienny kilof");
        this.polishNames.put(Material.STONE_AXE, "Kamienna siekiera");
        this.polishNames.put(Material.DIAMOND_SWORD, "Diamentowy miecz");
        this.polishNames.put(Material.DIAMOND_SPADE, "Diamentowa lopata");
        this.polishNames.put(Material.DIAMOND_PICKAXE, "Diamentowy kilof");
        this.polishNames.put(Material.DIAMOND_AXE, "Diamentowa siekiera");
        this.polishNames.put(Material.STICK, "Patyk");
        this.polishNames.put(Material.BOWL, "Miseczka");
        this.polishNames.put(Material.MUSHROOM_SOUP, "Zupa grzybowa");
        this.polishNames.put(Material.GOLD_SWORD, "Zloty miecz");
        this.polishNames.put(Material.GOLD_SPADE, "Zlota lopata");
        this.polishNames.put(Material.GOLD_PICKAXE, "Zloty kilof");
        this.polishNames.put(Material.GOLD_AXE, "Zlota siekiera");
        this.polishNames.put(Material.STRING, "Nitka");
        this.polishNames.put(Material.FEATHER, "Pioro");
        this.polishNames.put(Material.SULPHUR, "Proch strzelniczy");
        this.polishNames.put(Material.WOOD_HOE, "Drewniana motyka");
        this.polishNames.put(Material.STONE_HOE, "Kamienna motyka");
        this.polishNames.put(Material.IRON_HOE, "Zelazna motyka");
        this.polishNames.put(Material.DIAMOND_HOE, "Diemtnowa motyka");
        this.polishNames.put(Material.GOLD_HOE, "Zlota motyka");
        this.polishNames.put(Material.SEEDS, "Nasionka");
        this.polishNames.put(Material.WHEAT, "Pszenica");
        this.polishNames.put(Material.BREAD, "Chleb");
        this.polishNames.put(Material.LEATHER_HELMET, "Skorzany helm");
        this.polishNames.put(Material.LEATHER_CHESTPLATE, "Skorzana klata");
        this.polishNames.put(Material.LEATHER_LEGGINGS, "Skorzane spodnie");
        this.polishNames.put(Material.LEATHER_BOOTS, "Skorzane buty");
        this.polishNames.put(Material.CHAINMAIL_HELMET, "Helm z kolcza");
        this.polishNames.put(Material.CHAINMAIL_CHESTPLATE, "Klata z kolcza");
        this.polishNames.put(Material.CHAINMAIL_LEGGINGS, "Spodnie z kolcza");
        this.polishNames.put(Material.CHAINMAIL_BOOTS, "Buty z kolcza");
        this.polishNames.put(Material.IRON_HELMET, "Zelazny helm");
        this.polishNames.put(Material.IRON_CHESTPLATE, "Zelazna klata");
        this.polishNames.put(Material.IRON_LEGGINGS, "Zelazne spodnie");
        this.polishNames.put(Material.IRON_BOOTS, "Zelazne buty");
        this.polishNames.put(Material.DIAMOND_HELMET, "Diamentowy helm");
        this.polishNames.put(Material.DIAMOND_CHESTPLATE, "Diamentowa klata");
        this.polishNames.put(Material.DIAMOND_LEGGINGS, "Diamentowe spodnie");
        this.polishNames.put(Material.DIAMOND_BOOTS, "Diamentowe buty");
        this.polishNames.put(Material.GOLD_HELMET, "Zloty helm");
        this.polishNames.put(Material.GOLD_CHESTPLATE, "Zlota klata");
        this.polishNames.put(Material.GOLD_LEGGINGS, "Zlote spodnie");
        this.polishNames.put(Material.GOLD_BOOTS, "Zlote buty");
        this.polishNames.put(Material.FLINT, "Krzemien");
        this.polishNames.put(Material.PORK, "Schab");
        this.polishNames.put(Material.GRILLED_PORK, "Pieczony schab");
        this.polishNames.put(Material.PAINTING, "Obraz");
        this.polishNames.put(Material.GOLDEN_APPLE, "Zlote jablko");
        this.polishNames.put(Material.SIGN, "Znak");
        this.polishNames.put(Material.WOOD_DOOR, "Drewniane drzwi");
        this.polishNames.put(Material.BUCKET, "Wiaderko");
        this.polishNames.put(Material.WATER_BUCKET, "Wiaderko wody");
        this.polishNames.put(Material.LAVA_BUCKET, "Wiaderko lawy");
        this.polishNames.put(Material.MINECART, "Wagonik");
        this.polishNames.put(Material.SADDLE, "Siodlo");
        this.polishNames.put(Material.IRON_DOOR, "Zelazne drzwi");
        this.polishNames.put(Material.REDSTONE, "Czerwony proszek");
        this.polishNames.put(Material.SNOW_BALL, "Sniezka");
        this.polishNames.put(Material.BOAT, "Lodka");
        this.polishNames.put(Material.LEATHER, "Skora");
        this.polishNames.put(Material.MILK_BUCKET, "Wiaderko mleka");
        this.polishNames.put(Material.CLAY_BRICK, "Cegly");
        this.polishNames.put(Material.CLAY_BALL, "Kulka gliny");
        this.polishNames.put(Material.SUGAR_CANE, "Trzcina cukrowa");
        this.polishNames.put(Material.PAPER, "Papier");
        this.polishNames.put(Material.BOOK, "Ksiazka");
        this.polishNames.put(Material.SLIME_BALL, "Kulka szlamu");
        this.polishNames.put(Material.STORAGE_MINECART, "Wagonik");
        this.polishNames.put(Material.POWERED_MINECART, "Wagonik");
        this.polishNames.put(Material.EGG, "Jajko");
        this.polishNames.put(Material.COMPASS, "Kompas");
        this.polishNames.put(Material.FISHING_ROD, "Wedka");
        this.polishNames.put(Material.WATCH, "Zegar");
        this.polishNames.put(Material.GLOWSTONE_DUST, "Jasnopyl");
        this.polishNames.put(Material.RAW_FISH, "Ryba");
        this.polishNames.put(Material.COOKED_FISH, "Pieczona ryba");
        this.polishNames.put(Material.INK_SACK, "Czarny barwnik");
        this.polishNames.put(Material.BONE, "Kosc");
        this.polishNames.put(Material.SUGAR, "Cukier");
        this.polishNames.put(Material.CAKE, "Ciasto");
        this.polishNames.put(Material.BED, "Lozko");
        this.polishNames.put(Material.DIODE, "Przekaznik");
        this.polishNames.put(Material.COOKIE, "Ciastko");
        this.polishNames.put(Material.MAP, "Mapa");
        this.polishNames.put(Material.SHEARS, "Nozyce");
        this.polishNames.put(Material.MELON, "Arbuz");
        this.polishNames.put(Material.PUMPKIN_SEEDS, "Nasiono dyni");
        this.polishNames.put(Material.MELON_SEEDS, "Nasiono melona");
        this.polishNames.put(Material.RAW_BEEF, "Stek");
        this.polishNames.put(Material.COOKED_BEEF, "Pieczony stek");
        this.polishNames.put(Material.RAW_CHICKEN, "Kurczak");
        this.polishNames.put(Material.COOKED_CHICKEN, "Upieczony kurczak");
        this.polishNames.put(Material.ROTTEN_FLESH, "Zgnile mieso");
        this.polishNames.put(Material.ENDER_PEARL, "Perla endermana");
        this.polishNames.put(Material.BLAZE_ROD, "Palka blaza");
        this.polishNames.put(Material.GHAST_TEAR, "Lza gasta");
        this.polishNames.put(Material.GOLD_NUGGET, "Zloty samorodek");
        this.polishNames.put(Material.NETHER_STALK, "Brodawka netherowa");
        this.polishNames.put(Material.POTION, "Mikstura");
        this.polishNames.put(Material.GLASS_BOTTLE, "Szklana butelka");
        this.polishNames.put(Material.SPIDER_EYE, "Oko pajaka");
        this.polishNames.put(Material.FERMENTED_SPIDER_EYE, "Zfermentowane oko pajaka");
        this.polishNames.put(Material.BLAZE_POWDER, "Blaze powder");
        this.polishNames.put(Material.MAGMA_CREAM, "Magmowy krem");
        this.polishNames.put(Material.BREWING_STAND_ITEM, "Stol alchemiczny");
        this.polishNames.put(Material.CAULDRON_ITEM, "Kociol");
        this.polishNames.put(Material.EYE_OF_ENDER, "Sko kresu");
        this.polishNames.put(Material.SPECKLED_MELON, "Arbuz");
        this.polishNames.put(Material.MONSTER_EGG, "Jajko spawnujace");
        this.polishNames.put(Material.EXP_BOTTLE, "Butelka z expem");
        this.polishNames.put(Material.FIREBALL, "Kula ognia");
        this.polishNames.put(Material.BOOK_AND_QUILL, "Ksiazka z piorem");
        this.polishNames.put(Material.WRITTEN_BOOK, "Zapisana ksiazka");
        this.polishNames.put(Material.EMERALD, "Emerald");
        this.polishNames.put(Material.ITEM_FRAME, "Ramka na obraz");
        this.polishNames.put(Material.FLOWER_POT_ITEM, "Doniczka");
        this.polishNames.put(Material.CARROT_ITEM, "Marchewka");
        this.polishNames.put(Material.POTATO_ITEM, "Ziemniak");
        this.polishNames.put(Material.BAKED_POTATO, "Upieczony ziemniak");
        this.polishNames.put(Material.POISONOUS_POTATO, "Trujacy ziemniak");
        this.polishNames.put(Material.EMPTY_MAP, "Pusta mapa");
        this.polishNames.put(Material.GOLDEN_CARROT, "Zlota marchewka");
        this.polishNames.put(Material.SKULL_ITEM, "Glowa");
        this.polishNames.put(Material.CARROT_STICK, "Marchewka na patyku");
        this.polishNames.put(Material.NETHER_STAR, "Gwiazda netherowa");
        this.polishNames.put(Material.PUMPKIN_PIE, "Placek dyniowy");
        this.polishNames.put(Material.FIREWORK, "Fajerwerka");
        this.polishNames.put(Material.FIREWORK_CHARGE, "Fajerwerka");
        this.polishNames.put(Material.ENCHANTED_BOOK, "Enchantowana ksiazka");
        this.polishNames.put(Material.REDSTONE_COMPARATOR, "Komperator");
        this.polishNames.put(Material.NETHER_BRICK_ITEM, "Cegla netherowa");
        this.polishNames.put(Material.QUARTZ, "Kwarc");
        this.polishNames.put(Material.EXPLOSIVE_MINECART, "Wagonik z tnt");
        this.polishNames.put(Material.HOPPER_MINECART, "Wagonik z lejem");
        this.polishNames.put(Material.IRON_BARDING, "Zelazna motyka");
        this.polishNames.put(Material.GOLD_BARDING, "Zlota motyka");
        this.polishNames.put(Material.DIAMOND_BARDING, "Diamentowa motyka");
        this.polishNames.put(Material.LEASH, "Lasso");
        this.polishNames.put(Material.NAME_TAG, "Name tag");
        this.polishNames.put(Material.COMMAND_MINECART, "Wagonik z blokiem polecen");
        this.polishNames.put(Material.GOLD_RECORD, "Plyta muzyczna");
        this.polishNames.put(Material.GREEN_RECORD, "Plyta muzyczna");
        this.polishNames.put(Material.RECORD_3, "Plyta muzyczna");
        this.polishNames.put(Material.RECORD_4, "Plyta muzyczna");
        this.polishNames.put(Material.RECORD_5, "Plyta muzyczna");
        this.polishNames.put(Material.RECORD_6, "Plyta muzyczna");
        this.polishNames.put(Material.RECORD_7, "Plyta muzyczna");
        this.polishNames.put(Material.RECORD_8, "Plyta muzyczna");
        this.polishNames.put(Material.RECORD_9, "Plyta muzyczna");
        this.polishNames.put(Material.RECORD_10, "Plyta muzyczna");
        this.polishNames.put(Material.RECORD_11, "Plyta muzyczna");
        this.polishNames.put(Material.RECORD_12, "Plyta muzyczna");
        this.polishNames.put(Material.GOLDEN_APPLE, "Zlote Jablko");
    }

    public static String getName(Material m) {
        if (polishNames.containsKey(m)) {
            return polishNames.get(m);
        }
        return m.toString();
    }
}