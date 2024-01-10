--
-- PostgreSQL database dump
--

-- Dumped from database version 14.10
-- Dumped by pg_dump version 14.10

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: foods; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA foods;


ALTER SCHEMA foods OWNER TO postgres;

--
-- Name: tablefunc; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS tablefunc WITH SCHEMA foods;


--
-- Name: EXTENSION tablefunc; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION tablefunc IS 'functions that manipulate whole tables, including crosstab';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: food; Type: TABLE; Schema: foods; Owner: admin
--

CREATE TABLE foods.food (
    fdc_id integer NOT NULL,
    description text,
    food_category_id integer,
    food_kind text
);


ALTER TABLE foods.food OWNER TO postgres;

--
-- Name: food_backup; Type: TABLE; Schema: foods; Owner: admin
--

CREATE TABLE foods.food_backup (
    fdc_id integer,
    description text,
    food_category_id integer
);


ALTER TABLE foods.food_backup OWNER TO postgres;

--
-- Name: food_nutrient; Type: TABLE; Schema: foods; Owner: admin
--

CREATE TABLE foods.food_nutrient (
    id integer NOT NULL,
    fdc_id integer,
    nutrient_id integer,
    amount double precision
);


ALTER TABLE foods.food_nutrient OWNER TO postgres;

--
-- Name: nutrient; Type: TABLE; Schema: foods; Owner: admin
--

CREATE TABLE foods.nutrient (
    id integer NOT NULL,
    name text,
    unit_name text
);


ALTER TABLE foods.nutrient OWNER TO postgres;

--
-- Data for Name: food; Type: TABLE DATA; Schema: foods; Owner: admin
--

COPY foods.food (fdc_id, description, food_category_id, food_kind) FROM stdin;
2346397	Whole Grain, Steel Cut	20	Oats
2346398	Raw	9	Pineapple
2346399	Sweet, Dark Red, Raw	9	Cherries
2346400	Snap, Green, Raw	11	Beans
2346401	Russet, Without Skin, Raw	11	Potatoes
2346402	Red, Without Skin, Raw	11	Potatoes
2346403	Gold, Without Skin, Raw	11	Potatoes
2346404	Orange Flesh, Without Skin, Raw	11	Sweet potatoes
2346405	Raw	11	Celery
2346406	With Peel, Raw	11	Cucumber
2346407	Green, Raw	11	Cabbage
2346408	Red, Raw	11	Cabbage
2346409	Raw	9	Strawberries
2346410	Raw	9	Raspberries
2346411	Raw	9	Blueberries
2346412	Red, Seedless, Raw	9	Grapes
2346413	Green, Seedless, Raw	9	Grapes
2346414	Unsweetened, With Added Vitamin C	9	Applesauce
323121	Beef, Unheated	7	Frankfurter
323604	Whole, Raw, Frozen, Pasteurized	1	Egg
323697	White, Raw, Frozen, Pasteurized	1	Egg
329716	Yolk, Dried	1	Egg
332397	Sliced, Pre-Packaged, Deli Meat (96%Fat Free, Water Added)	7	Ham
746766	Ricotta, Whole Milk	1	Cheese
746767	Swiss	1	Cheese
746768	Dried, Uncooked	9	Figs
746769	Cos Or Romaine, Raw	11	Lettuce
746770	Cantaloupe, Raw	9	Melons
746771	Raw, Navels	9	Oranges
746772	Lowfat, Fluid, 1% Milkfat, With Added Vitamin A And Vitamin D	1	Milk
746773	Raw, Bartlett	9	Pears
746776	Nonfat, Fluid, With Added Vitamin A And Vitamin D (Fat Free Or Skim)	1	Milk
746777	Salsa, Ready-To-Serve	6	Sauce
746778	Reduced Fat, Fluid, 2% Milkfat, With Added Vitamin A And Vitamin D	1	Milk
746779	Breakfast Sausage, Beef, Pre-Cooked, Unprepared	7	Sausage
746780	Italian, Pork, Mild, Cooked, Pan-Fried	7	Sausage
746781	Pork, Chorizo, Link Or Ground, Cooked, Pan-Fried	7	Sausage
746782	Whole, 3.25% Milkfat, With Added Vitamin D	1	Milk
746783	Turkey, Breakfast Links, Mild, Raw	7	Sausage
746784	Granulated	19	Sugars
746785	Ground, 93% Lean, 7% Fat, Pan-Broiled Crumbles	5	Turkey
746952	Sliced, Restaurant	10	Ham
321358	Commercial	16	Hummus
321359	Reduced Fat, Fluid, 2% Milkfat, With Added Vitamin A And Vitamin D	1	Milk
321360	Grape, Raw	11	Tomatoes
747429	American, Restaurant	1	Cheese
747447	Raw	11	Broccoli
747448	Raw	9	Strawberries
321611	Snap, Green, Canned, Regular Pack, Drained Solids	11	Beans
321900	Raw	11	Broccoli
322228	Lowfat, Fluid, 1% Milkfat, With Added Vitamin A And Vitamin D	1	Milk
322559	Nonfat, Fluid, With Added Vitamin A And Vitamin D (Fat Free Or Skim)	1	Milk
322892	Whole, 3.25% Milkfat, With Added Vitamin D	1	Milk
747693	Restaurant	11	Ketchup
747997	Grade A, Large, Egg White	1	Eggs
748236	Grade A, Large, Egg Yolk	1	Eggs
748967	Grade A, Large, Egg Whole	1	Eggs
323294	Almonds, Dry Roasted, With Salt Added	12	Nuts
323444	Ricotta, Whole Milk	1	Cheese
323505	Raw	11	Kale
323793	White, Dried	1	Egg
324038	Salsa, Ready-To-Serve	6	Sauce
324157	Breakfast Sausage, Beef, Pre-Cooked, Unprepared	7	Sausage
324317	Breaded, Par Fried, Frozen, Prepared, Heated In Oven	11	Onion rings
324653	Cucumber, Dill Or Kosher Dill	11	Pickles
324860	Smooth Style, With Salt	16	Peanut butter
325036	Parmesan, Grated	1	Cheese
325198	Pasteurized Process, American, Vitamin D Fortified	1	Cheese
325287	White, Canned Or Bottled, Unsweetened	9	Grapefruit juice
325430	Yellow, Raw	9	Peaches
325524	Sunflower Seed Kernels, Dry Roasted, With Salt Added	12	Seeds
325658	Italian, Pork, Mild, Cooked, Pan-Fried	7	Sausage
325871	White, Commercially Prepared	18	Bread
325992	Turkey, Breakfast Links, Mild, Raw	7	Sausage
326135	Swiss	1	Cheese
326196	Frozen, Cooked, Boiled, Drained, Without Salt	11	Kale
326458	Frozen, Unprepared (Includes Foods For Usda'S Food Distribution Program)	11	Carrots
326698	Prepared, Yellow	2	Mustard
326905	Dried, Uncooked	9	Figs
327046	Green, Raw	9	Kiwifruit
327198	Cantaloupe, Raw	9	Melons
327357	Raw	9	Nectarines
327501	Raw, Navels (Includes Foods For Usda'S Food Distribution Program)	9	Oranges
327699	Raw	9	Strawberries
327923	Cos Or Romaine, Raw	11	Lettuce
328637	Cheddar	1	Cheese
328841	Cottage, Lowfat, 2% Milkfat	1	Cheese
329370	Mozzarella, Low Moisture, Part-Skim	1	Cheese
329490	Whole, Dried	1	Egg
329596	Yolk, Raw, Frozen, Pasteurized	1	Egg
329830	Dry White, Queso Seco	1	Cheese
330137	Greek, Plain, Nonfat	1	Yogurt
330415	Greek, Strawberry, Nonfat	1	Yogurt
330458	Coconut	4	Oil
330869	Ground, 93% Lean, 7% Fat, Pan-Broiled Crumbles	5	Turkey
331897	Broilers Or Fryers, Drumstick, Meat Only, Cooked, Braised	5	Chicken
331960	Broiler Or Fryers, Breast, Skinless, Boneless, Meat Only, Cooked, Braised	5	Chicken
332282	Pasta, Spaghetti/Marinara, Ready-To-Serve	6	Sauce
332597	Raw, Bartlett (Includes Foods For Usda'S Food Distribution Program)	9	Pears
332791	Green, Manzanilla, Stuffed With Pimiento	9	Olives
332864	Pork, Chorizo, Link Or Ground, Cooked, Pan-Fried	7	Sausage
333008	Oatmeal, Soft, With Raisins	18	Cookies
333281	Canned, Red, Ripe, Diced	11	Tomatoes
333374	Haddock, Raw	15	Fish
333476	Pollock, Raw	15	Fish
334194	Tuna, Light, Canned In Water, Drained Solids	15	Fish
334247	Granulated	19	Sugars
334849	Loin, Top Loin Steak, Boneless, Lip-On, Separable Lean Only, Trimmed To 1/8" Fat, Choice, Raw	13	Beef
334897	Loin, Tenderloin Roast, Separable Lean Only, Boneless, Trimmed To 0" Fat, Select, Cooked, Roasted	13	Beef
334898	Round, Eye Of Round Roast, Boneless, Separable Lean Only, Trimmed To 0" Fat, Select, Raw	13	Beef
334963	Round, Top Round Roast, Boneless, Separable Lean Only, Trimmed To 0" Fat, Select, Raw	13	Beef
335061	Short Loin, T-Bone Steak, Bone-In, Separable Lean Only, Trimmed To 1/8" Fat, Choice, Cooked, Grilled	13	Beef
335109	Short Loin, Porterhouse Steak, Separable Lean Only, Trimmed To 1/8" Fat, Select, Raw	13	Beef
335240	Whole-Wheat, Commercially Prepared	18	Bread
746765	Dry White, Queso Seco	1	Cheese
746758	Loin, Tenderloin Roast, Separable Lean Only, Boneless, Trimmed To 0" Fat, Select, Cooked, Roasted	13	Beef
746759	Loin, Top Loin Steak, Boneless, Lip-On, Separable Lean Only, Trimmed To 1/8" Fat, Choice, Raw	13	Beef
746760	Round, Eye Of Round Roast, Boneless, Separable Lean Only, Trimmed To 0" Fat, Select, Raw	13	Beef
746761	Round, Top Round Roast, Boneless, Separable Lean Only, Trimmed To 0" Fat, Select, Raw	13	Beef
746762	Short Loin, Porterhouse Steak, Separable Lean Only, Trimmed To 1/8" Fat, Select, Raw	13	Beef
746763	Short Loin, T-Bone Steak, Bone-In, Separable Lean Only, Trimmed To 1/8" Fat, Choice, Cooked, Grilled	13	Beef
746764	Frozen, Unprepared	11	Carrots
749420	Cured, Bacon, Cooked, Restaurant	10	Pork
789890	Wheat, All-Purpose, Enriched, Bleached	20	Flour
789951	Wheat, All-Purpose, Enriched, Unbleached	20	Flour
790085	Whole Wheat, Unenriched	20	Flour
790018	Wheat, All-Purpose, Unenriched, Unbleached	20	Flour
790146	Bread, White, Enriched, Unbleached	20	Flour
790214	Rice, White, Unenriched	20	Flour
790276	Corn, Yellow, Fine Meal, Enriched	20	Flour
790577	Red, Raw	11	Onions
790646	Yellow, Raw	11	Onions
790774	Overripe, Raw	9	Bananas
790991	Ripe And Slightly Ripe, Raw	9	Bananas
1104647	Raw	11	Garlic
1104705	Soy, Defatted	16	Flour
1104766	Soy, Full-Fat	16	Flour
1104812	Rice, Brown	20	Flour
1104867	Rice, Glutinous	20	Flour
1104913	Pastry, Unenriched, Unbleached	20	Flour
1104962	White, Raw	11	Onions
1105073	Overripe, Raw	9	Bananas
1105314	Ripe And Slightly Ripe, Raw	9	Bananas
1105430	Red Delicious, With Skin, Raw	9	Apples
1105547	Honeycrisp, With Skin, Raw	9	Apples
1105664	Granny Smith, With Skin, Raw	9	Apples
1105781	Gala, With Skin, Raw	9	Apples
1105897	Fuji, With Skin, Raw	9	Apples
1750337	Unsweetened, Plain, Shelf Stable	16	Soy milk
1750338	Unsweetened, Plain, Shelf Stable	14	Almond milk
1750339	Red Delicious, With Skin, Raw	9	Apples
1750340	Fuji, With Skin, Raw	9	Apples
1750341	Gala, With Skin, Raw	9	Apples
1750342	Granny Smith, With Skin, Raw	9	Apples
1750343	Honeycrisp, With Skin, Raw	9	Apples
1750344	Lion'S Mane	11	Mushroom
1750345	Oyster	11	Mushroom
1750346	Shiitake	11	Mushrooms
1750347	White Button	11	Mushrooms
1750352	Baby	11	Spinach
1750353	Mature	11	Spinach
1750354	Roma	11	Tomato
1999626	Lion'S Mane	11	Mushroom
1999627	Oyster	11	Mushroom
1999628	Shiitake	11	Mushrooms
1999629	White Button	11	Mushrooms
1999630	Unsweetened, Plain, Shelf Stable	16	Soy milk
1999631	Unsweetened, Plain, Shelf Stable	14	Almond milk
1999632	Baby	11	Spinach
1999633	Mature	11	Spinach
1999634	Roma	11	Tomato
2003586	00	20	Flour
2003587	Spelt, Whole Grain	20	Flour
2003588	Semolina, Coarse And Semi-Coarse	20	Flour
2003589	Semolina, Fine	20	Flour
2003590	With Added Vitamin C, From Concentrate, Shelf Stable	9	Apple juice
2003591	No Pulp, Not Fortified, From Concentrate, Refrigerated	9	Orange juice
2003592	Purple, With Added Vitamin C, From Concentrate, Shelf Stable	9	Grape juice
2003593	White, With Added Vitamin C, From Concentrate, Shelf Stable	9	Grape juice
2003594	Not Fortified, From Concentrate, Shelf Stable	9	Cranberry juice
2003595	Red, Not Fortified, Not From Concentrate, Refrigerated	9	Grapefruit juice
2003596	With Added Ingredients, From Concentrate, Shelf Stable	11	Tomato juice
2003597	No Pulp, Not Fortified, Not From Concentrate, Refrigerated	9	Orange juice
2003598	Portabella	11	Mushroom
2003599	King Oyster	11	Mushroom
2003600	Enoki	11	Mushroom
2003601	Crimini	11	Mushroom
2003602	Maitake	11	Mushroom
2003603	Beech	11	Mushroom
2003604	Pioppini	11	Mushroom
2257044	Sweetened, Plain, Refrigerated	16	Soy milk
2257045	Unsweetened, Plain, Refrigerated	14	Almond milk
2257046	Unsweetened, Plain, Refrigerated	14	Oat milk
2258586	Mature, Raw	11	Carrots
2258587	Baby, Raw	11	Carrots
2258588	Bell, Green, Raw	11	Peppers
2258589	Bell, Yellow, Raw	11	Peppers
2258590	Bell, Red, Raw	11	Peppers
2258591	Bell, Orange, Raw	11	Peppers
2259792	Low Fat	1	Buttermilk
2259793	Plain, Whole Milk	1	Yogurt
2259794	Greek, Plain, Whole Milk	1	Yogurt
2259795	Parmesan, Grated, Refrigerated	1	Cheese
2259796	Feta, Whole Milk, Crumbled	1	Cheese
2261420	Almond	12	Flour
2261421	Oat, Whole Grain	20	Flour
2261422	Potato	11	Flour
2262072	Creamy	16	Peanut butter
2262073	Creamy	12	Sesame butter
2262074	Creamy	12	Almond butter
2262075	Ground	12	Flaxseed
2263887	Raw	9	Strawberries
2263888	Raw	9	Raspberries
2263889	Raw	9	Blueberries
2263890	Red, Seedless, Raw	9	Grapes
2263891	Green, Seedless, Raw	9	Grapes
2263892	Unsweetened, With Added Vitamin C	9	Applesauce
2346384	Full Fat, Large Or Small Curd	1	Cottage cheese
2346385	Full Fat, Block	1	Cream cheese
2346386	Heavy	1	Cream
2346387	Sour, Full Fat	1	Cream
2346388	Iceberg, Raw	11	Lettuce
2346389	Romaine, Green, Raw	11	Lettuce
2346390	Leaf, Red, Raw	11	Lettuce
2346391	Leaf, Green, Raw	11	Lettuce
2346392	Pine Nuts, Raw	12	Nuts
2346393	Almonds, Whole, Raw	12	Nuts
2346394	Walnuts, English, Halves, Raw	12	Nuts
2346395	Pecans, Halves, Raw	12	Nuts
2346396	Whole Grain, Rolled, Old Fashioned	20	Oats
2512371	Amaranth	20	Flour
2512372	Quinoa	20	Flour
2512373	Sorghum	20	Flour
2512374	Buckwheat	20	Flour
2512375	Rye	20	Flour
2512376	Barley	20	Flour
2512377	Cassava	11	Flour
2512378	Whole Grain	20	Buckwheat
2512379	Whole Grain	20	Millet
2512380	Brown, Long Grain, Unenriched, Raw	20	Rice
2512381	White, Long Grain, Unenriched, Raw	20	Rice
2514743	Ground, 90% Lean Meat / 10% Fat, Raw	13	Beef
2514744	Ground, 80% Lean Meat / 20% Fat, Raw	13	Beef
2514745	Ground, Raw	10	Pork
2514746	Ground, With Additives, Raw	5	Chicken
2514747	Ground, 93% Lean/ 7% Fat, Raw	5	Turkey
2515373	Brazilnuts, Raw	12	Nuts
2515374	Cashew Nuts, Raw	12	Nuts
2515375	Hazelnuts Or Filberts, Raw	12	Nuts
2515376	Raw	16	Peanuts
2515377	Chestnut	12	Flour
2515378	Macadamia Nuts, Raw	12	Nuts
2515379	Pistachio Nuts, Raw	12	Nuts
2515380	Pumpkin Seeds (Pepitas), Raw	12	Seeds
2515381	Sunflower Seed, Kernel, Raw	12	Seeds
2515382	Coconut	12	Flour
2644281	Cannellini, Dry	16	Beans
2644282	(Garbanzo Beans, Bengal Gram), Dry	16	Chickpeas
2644283	Dry	16	Lentils
2644284	Dry	16	Blackeye pea
2644285	Black, Canned, Sodium Added, Drained And Rinsed	16	Beans
2644286	Navy, Canned, Sodium Added, Drained And Rinsed	16	Beans
2644287	Cannellini, Canned, Sodium Added, Drained And Rinsed	16	Beans
2644288	Bengal Gram), Canned, Sodium Added, Drained And Rinsed	16	Chickpeas (garbanzo beans
2644289	Kidney, Dark Red, Canned, Sodium Added, Sugar Added, Drained And Rinsed	16	Beans
2644290	Kidney, Light Red, Canned, Sodium Added, Sugar Added, Drained And Rinsed	16	Beans
2644291	Green, Sweet, Canned, Sodium Added, Sugar Added, Drained And Rinsed	11	Peas
2644292	Pinto, Canned, Sodium Added, Drained And Rinsed	16	Beans
2644293	Canned, Sodium Added, Drained And Rinsed	16	Blackeye pea
2644294	Great Northern, Canned, Sodium Added, Drained And Rinsed	16	Beans
2646168	Loin, Boneless, Raw	10	Pork
2646169	Loin, Tenderloin, Boneless, Raw	10	Pork
2646170	Breast, Boneless, Skinless, Raw	5	Chicken
2646171	Thigh, Boneless, Skinless, Raw	5	Chicken
2646172	Ribeye, Steak, Boneless, Choice, Raw	13	Beef
2646173	Round, Top Round, Boneless, Choice, Raw	13	Beef
2646174	Chuck, Roast, Boneless, Choice, Raw	13	Beef
2646175	Flank, Steak, Boneless, Choice, Raw	13	Beef
2647437	Plain, Nonfat	1	Yogurt
2647438	Monterey Jack, Solid	1	Cheese
2647439	Pasteurized Process Cheese Food Or Product, American, Singles	1	Cheese
2647440	Provolone, Sliced	1	Cheese
2647441	Oaxaca, Solid	1	Cheese
2647442	Queso Fresco, Solid	1	Cheese
2647443	Cotija, Solid	1	Cheese
\.


--
-- Data for Name: food_backup; Type: TABLE DATA; Schema: foods; Owner: admin
--

COPY foods.food_backup (fdc_id, description, food_category_id) FROM stdin;
323121	Frankfurter, beef, unheated	7
323604	Egg, whole, raw, frozen, pasteurized	1
323697	Egg, white, raw, frozen, pasteurized	1
329716	Egg, yolk, dried	1
332397	Ham, sliced, pre-packaged, deli meat (96%fat free, water added)	7
746766	Cheese, ricotta, whole milk	1
746767	Cheese, swiss	1
746768	Figs, dried, uncooked	9
746769	Lettuce, cos or romaine, raw	11
746770	Melons, cantaloupe, raw	9
746771	Oranges, raw, navels	9
746772	Milk, lowfat, fluid, 1% milkfat, with added vitamin A and vitamin D	1
746773	Pears, raw, bartlett	9
746774	Restaurant, Chinese, sweet and sour pork	25
746776	Milk, nonfat, fluid, with added vitamin A and vitamin D (fat free or skim)	1
746777	Sauce, salsa, ready-to-serve	6
746778	Milk, reduced fat, fluid, 2% milkfat, with added vitamin A and vitamin D	1
746779	Sausage, breakfast sausage, beef, pre-cooked, unprepared	7
746780	Sausage, Italian, pork, mild, cooked, pan-fried	7
746781	Sausage, pork, chorizo, link or ground, cooked, pan-fried	7
746782	Milk, whole, 3.25% milkfat, with added vitamin D	1
746783	Sausage, turkey, breakfast links, mild, raw	7
746784	Sugars, granulated	19
746785	Turkey, ground, 93% lean, 7% fat, pan-broiled crumbles	5
746952	Ham, sliced, restaurant	10
321358	Hummus, commercial	16
321359	Milk, reduced fat, fluid, 2% milkfat, with added vitamin A and vitamin D	1
321360	Tomatoes, grape, raw	11
747429	Cheese, American, restaurant	1
747447	Broccoli, raw	11
747448	Strawberries, raw	9
321611	Beans, snap, green, canned, regular pack, drained solids	11
321900	Broccoli, raw	11
322228	Milk, lowfat, fluid, 1% milkfat, with added vitamin A and vitamin D	1
322559	Milk, nonfat, fluid, with added vitamin A and vitamin D (fat free or skim)	1
322892	Milk, whole, 3.25% milkfat, with added vitamin D	1
747693	Ketchup, restaurant	11
747997	Eggs, Grade A, Large, egg white	1
748236	Eggs, Grade A, Large, egg yolk	1
748967	Eggs, Grade A, Large, egg whole	1
323294	Nuts, almonds, dry roasted, with salt added	12
323444	Cheese, ricotta, whole milk	1
323505	Kale, raw	11
323793	Egg, white, dried	1
324038	Sauce, salsa, ready-to-serve	6
324157	Sausage, breakfast sausage, beef, pre-cooked, unprepared	7
324317	Onion rings, breaded, par fried, frozen, prepared, heated in oven	11
324653	Pickles, cucumber, dill or kosher dill	11
324860	Peanut butter, smooth style, with salt	16
325036	Cheese, parmesan, grated	1
325198	Cheese, pasteurized process, American, vitamin D fortified	1
325287	Grapefruit juice, white, canned or bottled, unsweetened	9
325430	Peaches, yellow, raw	9
325524	Seeds, sunflower seed kernels, dry roasted, with salt added	12
325658	Sausage, Italian, pork, mild, cooked, pan-fried	7
325871	Bread, white, commercially prepared	18
325992	Sausage, turkey, breakfast links, mild, raw	7
326135	Cheese, swiss	1
326196	Kale, frozen, cooked, boiled, drained, without salt	11
326458	Carrots, frozen, unprepared (Includes foods for USDA's Food Distribution Program)	11
326698	Mustard, prepared, yellow	2
326905	Figs, dried, uncooked	9
327046	Kiwifruit, green, raw	9
327198	Melons, cantaloupe, raw	9
327357	Nectarines, raw	9
327501	Oranges, raw, navels (Includes foods for USDA's Food Distribution Program)	9
327699	Strawberries, raw	9
327923	Lettuce, cos or romaine, raw	11
328637	Cheese, cheddar	1
328841	Cheese, cottage, lowfat, 2% milkfat	1
329370	Cheese, mozzarella, low moisture, part-skim	1
329490	Egg, whole, dried	1
329596	Egg, yolk, raw, frozen, pasteurized	1
329830	Cheese, dry white, queso seco	1
330137	Yogurt, Greek, plain, nonfat	1
330415	Yogurt, Greek, strawberry, nonfat	1
330458	Oil, coconut	4
330869	Turkey, ground, 93% lean, 7% fat, pan-broiled crumbles	5
331897	Chicken, broilers or fryers, drumstick, meat only, cooked, braised	5
331960	Chicken, broiler or fryers, breast, skinless, boneless, meat only, cooked, braised	5
332282	Sauce, pasta, spaghetti/marinara, ready-to-serve	6
332597	Pears, raw, bartlett (Includes foods for USDA's Food Distribution Program)	9
332791	Olives, green, Manzanilla, stuffed with pimiento	9
332864	Sausage, pork, chorizo, link or ground, cooked, pan-fried	7
333008	Cookies, oatmeal, soft, with raisins	18
333281	Tomatoes, canned, red, ripe, diced	11
333374	Fish, haddock, raw	15
333476	Fish, pollock, raw	15
334194	Fish, tuna, light, canned in water, drained solids	15
334247	Sugars, granulated	19
334462	Restaurant, Chinese, sweet and sour pork	25
334536	Restaurant, Chinese, fried rice, without meat	25
334628	Restaurant, Latino, tamale, pork	25
334720	Restaurant, Latino, pupusas con frijoles (pupusas, bean)	25
334849	Beef, loin, top loin steak, boneless, lip-on, separable lean only, trimmed to 1/8" fat, choice, raw	13
334897	Beef, loin, tenderloin roast, separable lean only, boneless, trimmed to 0" fat, select, cooked, roasted	13
334898	Beef, round, eye of round roast, boneless, separable lean only, trimmed to 0" fat, select, raw	13
334963	Beef, round, top round roast, boneless, separable lean only, trimmed to 0" fat, select, raw	13
335061	Beef, short loin, t-bone steak, bone-in, separable lean only, trimmed to 1/8" fat, choice, cooked, grilled	13
335109	Beef, short loin, porterhouse steak, separable lean only, trimmed to 1/8" fat, select, raw	13
335240	Bread, whole-wheat, commercially prepared	18
746765	Cheese, dry white, queso seco	1
746758	Beef, loin, tenderloin roast, separable lean only, boneless, trimmed to 0" fat, select, cooked, roasted	13
746759	Beef, loin, top loin steak, boneless, lip-on, separable lean only, trimmed to 1/8" fat, choice, raw	13
746760	Beef, round, eye of round roast, boneless, separable lean only, trimmed to 0" fat, select, raw	13
746761	Beef, round, top round roast, boneless, separable lean only, trimmed to 0" fat, select, raw	13
746762	Beef, short loin, porterhouse steak, separable lean only, trimmed to 1/8" fat, select, raw	13
746763	Beef, short loin, t-bone steak, bone-in, separable lean only, trimmed to 1/8" fat, choice, cooked, grilled	13
746764	Carrots, frozen, unprepared	11
749420	Pork, cured, bacon, cooked, restaurant	10
789890	Flour, wheat, all-purpose, enriched, bleached	20
789951	Flour, wheat, all-purpose, enriched, unbleached	20
790085	Flour, whole wheat, unenriched	20
790018	Flour, wheat, all-purpose, unenriched, unbleached	20
790146	Flour, bread, white, enriched, unbleached	20
790214	Flour, rice, white, unenriched	20
790276	Flour, corn, yellow, fine meal, enriched	20
790577	Onions, red, raw	11
790646	Onions, yellow, raw	11
790774	Bananas, overripe, raw	9
790991	Bananas, ripe and slightly ripe, raw	9
1104647	Garlic, raw	11
1104705	Flour, soy, defatted	16
1104766	Flour, soy, full-fat	16
1104812	Flour, rice, brown	20
1104867	Flour, rice, glutinous	20
1104913	Flour, pastry, unenriched, unbleached	20
1104962	Onions, white, raw	11
1105073	Bananas, overripe, raw	9
1105314	Bananas, ripe and slightly ripe, raw	9
1105430	Apples, red delicious, with skin, raw	9
1105547	Apples, honeycrisp, with skin, raw	9
1105664	Apples, granny smith, with skin, raw	9
1105781	Apples, gala, with skin, raw	9
1105897	Apples, fuji, with skin, raw	9
1750337	Soy milk, unsweetened, plain, shelf stable	16
1750338	Almond milk, unsweetened, plain, shelf stable	14
1750339	Apples, red delicious, with skin, raw	9
1750340	Apples, fuji, with skin, raw	9
1750341	Apples, gala, with skin, raw	9
1750342	Apples, granny smith, with skin, raw	9
1750343	Apples, honeycrisp, with skin, raw	9
1750344	Mushroom, lion's mane	11
1750345	Mushroom, oyster	11
1750346	Mushrooms, shiitake	11
1750347	Mushrooms, white button	11
1750352	Spinach, baby	11
1750353	Spinach, mature	11
1750354	Tomato, roma	11
1999626	Mushroom, lion's mane	11
1999627	Mushroom, oyster	11
1999628	Mushrooms, shiitake	11
1999629	Mushrooms, white button	11
1999630	Soy milk, unsweetened, plain, shelf stable	16
1999631	Almond milk, unsweetened, plain, shelf stable	14
1999632	Spinach, baby	11
1999633	Spinach, mature	11
1999634	Tomato, roma	11
2003586	Flour, 00	20
2003587	Flour, spelt, whole grain	20
2003588	Flour, semolina, coarse and semi-coarse	20
2003589	Flour, semolina, fine	20
2003590	Apple juice, with added vitamin C, from concentrate, shelf stable	9
2003591	Orange juice, no pulp, not fortified, from concentrate, refrigerated	9
2003592	Grape juice, purple, with added vitamin C, from concentrate, shelf stable	9
2003593	Grape juice, white, with added vitamin C, from concentrate, shelf stable	9
2003594	Cranberry juice, not fortified, from concentrate, shelf stable	9
2003595	Grapefruit juice, red, not fortified, not from concentrate, refrigerated	9
2003596	Tomato juice, with added ingredients, from concentrate, shelf stable	11
2003597	Orange juice, no pulp, not fortified, not from concentrate, refrigerated	9
2003598	Mushroom, portabella	11
2003599	Mushroom, king oyster	11
2003600	Mushroom, enoki	11
2003601	Mushroom, crimini	11
2003602	Mushroom, maitake	11
2003603	Mushroom, beech	11
2003604	Mushroom, pioppini	11
2257044	Soy milk, sweetened, plain, refrigerated	16
2257045	Almond milk, unsweetened, plain, refrigerated	14
2257046	Oat milk, unsweetened, plain, refrigerated	14
2258586	Carrots, mature, raw	11
2258587	Carrots, baby, raw	11
2258588	Peppers, bell, green, raw	11
2258589	Peppers, bell, yellow, raw	11
2258590	Peppers, bell, red, raw	11
2258591	Peppers, bell, orange, raw	11
2259792	Buttermilk, low fat	1
2259793	Yogurt, plain, whole milk	1
2259794	Yogurt, Greek, plain, whole milk	1
2259795	Cheese, parmesan, grated, refrigerated	1
2259796	Cheese, feta, whole milk, crumbled	1
2261420	Flour, almond	12
2261421	Flour, oat, whole grain	20
2261422	Flour, potato	11
2262072	Peanut butter, creamy	16
2262073	Sesame butter, creamy	12
2262074	Almond butter, creamy	12
2262075	Flaxseed, ground	12
2263887	Strawberries, raw	9
2263888	Raspberries, raw	9
2263889	Blueberries, raw	9
2263890	Grapes, red, seedless, raw	9
2263891	Grapes, green, seedless, raw	9
2263892	Applesauce, unsweetened, with added vitamin C	9
2346384	Cottage cheese, full fat, large or small curd	1
2346385	Cream cheese, full fat, block	1
2346386	Cream, heavy	1
2346387	Cream, sour, full fat	1
2346388	Lettuce, iceberg, raw	11
2346389	Lettuce, romaine, green, raw	11
2346390	Lettuce, leaf, red, raw	11
2346391	Lettuce, leaf, green, raw	11
2346392	Nuts, pine nuts, raw	12
2346393	Nuts, almonds, whole, raw	12
2346394	Nuts, walnuts, English, halves, raw	12
2346395	Nuts, pecans, halves, raw	12
2346396	Oats, whole grain, rolled, old fashioned	20
2346397	Oats, whole grain, steel cut	20
2346398	Pineapple, raw	9
2346399	Cherries, sweet, dark red, raw	9
2346400	Beans, snap, green, raw	11
2346401	Potatoes, russet, without skin, raw	11
2346402	Potatoes, red, without skin, raw	11
2346403	Potatoes, gold, without skin, raw	11
2346404	Sweet potatoes, orange flesh, without skin, raw	11
2346405	Celery, raw	11
2346406	Cucumber, with peel, raw	11
2346407	Cabbage, green, raw	11
2346408	Cabbage, red, raw	11
2346409	Strawberries, raw	9
2346410	Raspberries, raw	9
2346411	Blueberries, raw	9
2346412	Grapes, red, seedless, raw	9
2346413	Grapes, green, seedless, raw	9
2346414	Applesauce, unsweetened, with added vitamin C	9
2512371	Flour, amaranth	20
2512372	Flour, quinoa	20
2512373	Flour, sorghum	20
2512374	Flour, buckwheat	20
2512375	Flour, rye	20
2512376	Flour, barley	20
2512377	Flour, cassava	11
2512378	Buckwheat, whole grain	20
2512379	Millet, whole grain	20
2512380	Rice, brown, long grain, unenriched, raw	20
2512381	Rice, white, long grain, unenriched, raw	20
2514743	Beef, ground, 90% lean meat / 10% fat, raw	13
2514744	Beef, ground, 80% lean meat / 20% fat, raw	13
2514745	Pork, ground, raw	10
2514746	Chicken, ground, with additives, raw	5
2514747	Turkey, ground, 93% lean/ 7% fat, raw	5
2515373	Nuts, brazilnuts, raw	12
2515374	Nuts, cashew nuts, raw	12
2515375	Nuts, hazelnuts or filberts, raw	12
2515376	Peanuts, raw	16
2515377	Flour, chestnut	12
2515378	Nuts, macadamia nuts, raw	12
2515379	Nuts, pistachio nuts, raw	12
2515380	Seeds, pumpkin seeds (pepitas), raw	12
2515381	Seeds, sunflower seed, kernel, raw	12
2515382	Flour, coconut	12
2644281	Beans, cannellini, dry	16
2644282	Chickpeas, (garbanzo beans, bengal gram), dry	16
2644283	Lentils, dry	16
2644284	Blackeye pea, dry	16
2644285	Beans, black, canned, sodium added, drained and rinsed	16
2644286	Beans, navy, canned, sodium added, drained and rinsed	16
2644287	Beans, cannellini, canned, sodium added, drained and rinsed	16
2644288	Chickpeas (garbanzo beans, bengal gram), canned, sodium added, drained and rinsed	16
2644289	Beans, kidney, dark red, canned, sodium added, sugar added, drained and rinsed	16
2644290	Beans, kidney, light red, canned, sodium added, sugar added, drained and rinsed	16
2644291	Peas, green, sweet, canned, sodium added, sugar added, drained and rinsed	11
2644292	Beans, pinto, canned, sodium added, drained and rinsed	16
2644293	Blackeye pea, canned, sodium added, drained and rinsed	16
2644294	Beans, great northern, canned, sodium added, drained and rinsed	16
2646168	Pork, loin, boneless, raw	10
2646169	Pork, loin, tenderloin, boneless, raw	10
2646170	Chicken, breast, boneless, skinless, raw	5
2646171	Chicken, thigh, boneless, skinless, raw	5
2646172	Beef, ribeye, steak, boneless, choice, raw	13
2646173	Beef, round, top round, boneless, choice, raw	13
2646174	Beef, chuck, roast, boneless, choice, raw	13
2646175	Beef, flank, steak, boneless, choice, raw	13
2647437	Yogurt, plain, nonfat	1
2647438	Cheese, monterey jack, solid	1
2647439	Cheese, pasteurized process cheese food or product, American, singles	1
2647440	Cheese, provolone, sliced	1
2647441	Cheese, oaxaca, solid	1
2647442	Cheese, queso fresco, solid	1
2647443	Cheese, cotija, solid	1
\.


--
-- Data for Name: food_nutrient; Type: TABLE DATA; Schema: foods; Owner: admin
--

COPY foods.food_nutrient (id, fdc_id, nutrient_id, amount) FROM stdin;
21115660	1750338	1085	1.1089
21115515	1750338	2048	14.5562
21115514	1750338	2047	14.5562
21115513	1750338	1005	0.3371125
21115233	1750338	1004	1.221
21115519	1750339	2048	55.622745
21115518	1750339	2047	61.7893
21115517	1750339	1005	14.7817
21115269	1750339	1004	0.2125
21115523	1750340	2048	58.20306
21115522	1750340	2047	64.6609
21115521	1750340	1005	15.6511625
21115474	1750340	1004	0.1625
21115527	1750341	2048	54.866865
21115526	1750341	2047	60.9536
21115525	1750341	1005	14.7680875
21115499	1750341	1004	0.15
21115531	1750342	2048	52.958085
21115530	1750342	2047	58.8719
21115529	1750342	1005	14.142975
21115424	1750342	1004	0.1375
21115535	1750343	2048	54.033225
21115534	1750343	2047	60.034
21115533	1750343	1005	14.6819375
21115449	1750343	1004	0.1
21115539	1750344	2048	35.107387
21115538	1750344	2047	42.6655
21115537	1750344	1005	7.5897
21114816	1750344	1004	0.2563
21115543	1750345	2048	33.31965
21115542	1750345	2047	41.0475
21115541	1750345	1005	6.94125
21114835	1750345	1004	0.1875
21115547	1750346	2048	36.3915875
21115546	1750346	2047	44.095
21115545	1750346	1005	8.170625
21114778	1750346	1004	0.195
21115551	1750347	2048	24.8732585
21115550	1750347	2047	31.2172
21115549	1750347	1005	4.079375
21114797	1750347	1004	0.3708
21115555	1750352	2048	20.72851125
21115554	1750352	2047	26.602
21115553	1750352	1005	2.406325
21114733	1750352	1004	0.6188
21115559	1750353	2048	21.5796975
21115558	1750353	2047	27.63
21115557	1750353	1005	2.64135
21114765	1750353	1004	0.6044
21115563	1750354	2048	18.95436075
21115562	1750354	2047	21.9574
21115561	1750354	1005	3.837475
21114701	1750354	1004	0.425
2219788	321358	1008	229
2219740	321358	1003	7.35
2219829	321359	1003	3.35
2219881	321359	1008	50
2220034	321360	1008	27
2220031	321360	1003	0.83
2220543	321611	1003	1.04
2220533	321611	1008	21
2221316	321900	1003	2.57
2221279	321900	1008	32
2219825	321358	1085	16.1
2219786	321358	1004	17.1
2219787	321358	1005	14.9
13338510	321358	2047	243
13338412	321358	2048	229
2219880	321359	1005	4.91
2219980	321359	1085	1.64
2219914	321359	1004	1.9
2220032	321360	1005	5.51
2219986	321360	1004	0.63
13338494	321360	2047	31
13338396	321360	2048	27
2222953	322228	1003	3.38
8513929	746758	1005	0
8513928	746758	1004	6.36
2222981	322228	1008	43
8513992	746758	1085	5.56
13338546	746758	2047	168
13338448	746758	2048	176
8514008	746759	1005	0
8514007	746759	1004	6.39
8514079	746759	1085	5.93
13338543	746759	2047	149
13338445	746759	2048	155
8514108	746760	1005	0
8514107	746760	1004	2.48
8514144	746760	1085	2.23
13338549	746760	2047	116
13338451	746760	2048	122
8514231	746761	1085	2.14
8514145	746761	1004	2.41
2220532	321611	1005	4.11
2220531	321611	1004	0.39
13338489	321611	2047	24
13338391	321611	2048	20
8514146	746761	1005	0
13338553	746761	2047	116
13338455	746761	2048	123
8514296	746762	1085	4.5
2259098	331897	1004	5.95
2259150	331897	1085	5.23
2259099	331897	1005	0
8514283	746762	1005	0
8514232	746762	1004	5.32
13338544	746762	2047	139
13338446	746762	2048	145
8514361	746763	1085	10.5
8514348	746763	1005	0
13338545	331897	2047	149
13338447	331897	2048	156
8514345	746763	1004	11.4
13338541	746763	2047	212
13338443	746763	2048	219
8514443	746764	1085	0.33
8514368	746764	1004	0.47
8514411	746764	1005	7.92
13338511	746764	2047	39
13338413	746764	2048	37
8514515	746765	1005	2.07
8514514	746765	1004	24.3
8514597	746765	1085	21.4
13338524	746765	2047	325
13338426	746765	2048	326
8514661	746766	1004	11
8514662	746766	1005	6.86
8514713	746766	1085	10.3
13338516	746766	2047	158
13338418	746766	2048	157
8514836	746767	1085	27.6
8514744	746767	1004	31
8514745	746767	1005	1.44
13338520	746767	2047	393
13338422	746767	2048	393
8514876	746768	1004	0.92
8514838	746768	1005	63.9
2259605	331960	1085	3.05
2259525	331960	1005	0
2259524	331960	1004	3.24
13338548	331960	2047	158
13338450	331960	2048	166
13338499	746768	2047	277
13338401	746768	2048	249
8514933	746769	1004	0.26
8514928	746769	1005	3.24
13338490	746769	2047	20
13338392	746769	2048	17
2224695	322559	1003	3.43
2224702	322559	1008	34
8515031	746770	1004	0.18
8514985	746770	1005	8.16
13338498	746770	2047	38
13338400	746770	2048	34
8515094	746771	1005	11.8
2221370	321900	1085	0.07
8515073	746771	1004	0.15
13338500	746771	2047	52
13338402	746771	2048	47
2221296	321900	1004	0.34
2221278	321900	1005	6.29
8515257	746772	1085	0.85
8515137	746772	1005	5.18
8515119	746772	1004	0.95
13338521	746772	2047	43
13338423	746772	2048	43
8515293	746773	1004	0.16
8515294	746773	1005	15.1
13338501	746773	2047	63
13338403	746773	2048	57
8515488	746776	1005	4.92
8515478	746776	1004	0.08
8515622	746776	1085	0.07
13338522	746776	2047	34
13338424	746776	2048	34
8515633	746777	1005	6.74
8515642	746777	1004	0.19
13338491	746777	2047	34
13338393	746777	2048	29
8515759	746778	1004	1.9
8515825	746778	1085	1.64
8515725	746778	1005	4.9
13338523	746778	2047	50
13338425	746778	2048	50
8515956	746779	1085	27.1
8515856	746779	1005	3.37
8515855	746779	1004	28.7
13338530	746779	2047	341
13338432	746779	2048	346
8515975	746780	1005	2.15
8516069	746780	1085	25.8
8515974	746780	1004	26.2
13338531	746780	2047	317
13338433	746780	2048	322
8516171	746781	1085	26
8516113	746781	1004	28.1
8516172	746781	1005	2.63
2226468	322892	1003	3.28
2226446	322892	1008	60
8516330	746782	1085	2.77
8516211	746782	1005	4.63
8516189	746782	1004	3.2
13338514	746782	2047	61
13338416	746782	2048	60
8516454	746783	1085	8.86
8516375	746783	1004	10.4
8516376	746783	1005	0.93
13338538	746783	2047	164
13338440	746783	2048	169
8516466	746784	1004	0.32
8516475	746784	1005	99.6
13338513	746784	2047	401
13338415	746784	2048	385
8516500	746785	1004	11.6
8516555	746785	1005	0
8516578	746785	1085	10.4
13338540	746785	2047	213
13338442	746785	2048	220
2261047	332282	1004	1.48
2261099	332282	1085	1.05
2261048	332282	1005	8.05
13338495	332282	2047	51
13338397	332282	2048	45
2227751	323121	1003	11.7
2227686	323121	1008	314
2223100	322228	1085	0.85
2222962	322228	1004	0.95
2222980	322228	1005	5.19
2228481	323294	1008	620
2228493	323294	1003	20.4
31222705	2512371	2048	377.9609
31222704	2512371	2047	384.076
31222703	2512371	1005	68.7787
31222547	2512371	1004	6.236
31222709	2512372	2048	378.394015
31222708	2512372	2047	385.197
31222707	2512372	1005	69.52465
31222497	2512372	1004	6.601
31222713	2512373	2048	363.8590417
31222712	2512373	2047	374.93
31222711	2512373	1005	77.39123
31222522	2512373	1004	3.586
31222717	2512374	2048	334.3016028
31222716	2512374	2047	357.968
31222715	2512374	1005	75.01808
2261781	332397	1085	3.15
2261741	332397	1004	3.73
31222447	2512374	1004	2.484
31222721	2512375	2048	350.962038
31222720	2512375	2047	359.4
31222719	2512375	1005	77.1618
31222472	2512375	1004	1.908
31222725	2512376	2048	357.191828
31222724	2512376	2047	366.53
31222723	2512376	1005	77.39832
31222670	2512376	1004	2.45
31222729	2512377	2048	358.55706075
31222728	2512377	2047	357.369
31222727	2512377	1005	87.312975
2261742	332397	1005	0.27
13338547	332397	2047	101
13338449	332397	2048	106
31222695	2512377	1004	0.4938
31222733	2512378	2048	331.6005206
31222732	2512378	2047	356.135
31222731	2512378	1005	71.13066
31222620	2512378	1004	3.039
31222737	2512379	2048	380.6064975
31222736	2512379	2047	375.618
31222735	2512379	1005	74.44623
31222645	2512379	1004	4.194
31222741	2512380	2048	368.3584745
31222740	2512380	2047	365.518
31222739	2512380	1005	76.68795
31222562	2512380	1004	3.306
31222745	2512381	2048	369.637321
31222744	2512381	2047	358.705
31222743	2512381	1005	80.31315
31222586	2512381	1004	1.033
2229341	323444	1008	157
2229304	323444	1003	7.81
2229550	323505	1003	2.92
2229572	323505	1008	35
2229859	323604	1008	150
2229852	323604	1003	12.3
2230128	323697	1008	48
2230130	323697	1003	10.1
2262322	332597	1005	15.1
2262321	332597	1004	0.16
2230380	323793	1003	79.9
2230394	323793	1008	376
8518378	746952	1005	2.36
8518359	746952	1004	3.68
8518377	746952	1085	3.54
13338564	746952	2047	121
2230983	324038	1008	29
13338459	746952	2048	126
2230974	324038	1003	1.44
2231514	324157	1003	13.3
2231466	324157	1008	328
2263039	332791	1004	12.9
2263140	332791	1085	11.8
2224691	322559	1004	0.08
2224701	322559	1005	4.89
2224835	322559	1085	0.07
2263141	332791	1005	4.96
13338505	332791	2047	141
13338407	332791	2048	130
2232241	324317	1003	4.52
2232168	324317	1008	288
2263567	332864	1005	2.63
2263566	332864	1085	26
2263508	332864	1004	28.1
2233036	324653	1003	0.48
2233050	324653	1008	12
2264177	333008	1085	13.7
2264178	333008	1005	69.6
2264077	333008	1004	14.3
13338557	333008	2047	430
2234234	324860	1003	22.5
31223301	2514743	1004	12.85
31223363	2514743	1005	0
31223364	2514743	2047	184.8648
31223365	2514743	2048	190.134819
31223343	2514744	1004	19.44
31223367	2514744	1005	0
31223368	2514744	2047	242.61
31223369	2514744	2048	247.812675
2234281	324860	1008	597
31223357	2514745	1004	17.49
31223371	2514745	1005	0
31223372	2514745	2047	228.2728
31223373	2514745	2048	233.442059
31223315	2514746	1004	7.161
31223375	2514746	1005	0
31223376	2514746	2047	133.0498
31223377	2514746	2048	138.128494
31223329	2514747	1004	9.591
31223379	2514747	1005	0
31223380	2514747	2047	152.615
31223381	2514747	2048	157.5897
2264822	333281	1004	0.5
2264832	333281	1005	3.32
13338493	333281	2047	21
13338395	333281	2048	18
2226423	322892	1004	3.2
2226445	322892	1005	4.67
2226564	322892	1085	2.77
2235089	325036	1008	421
2235128	325036	1003	29.6
31224734	2515373	1004	57.43
31224742	2515373	1005	21.64216
31224743	2515373	2047	663.586
31224744	2515373	2048	620.950526
31224704	2515374	1004	38.86
31224746	2515374	1005	36.2887
31224747	2515374	2047	564.664
31224748	2515374	2048	533.47799
31224719	2515375	1004	53.49
31224750	2515375	1005	26.5012
31224751	2515375	2047	641.39
31224752	2515375	2048	602.39467
31224673	2515376	1004	43.28
31224754	2515376	1005	26.498
31224755	2515376	2047	588.332
31224756	2515376	2048	550.62181
31224760	2515377	2048	384.58086
31224689	2515377	1004	4.636
31224758	2515377	1005	80.45025
31224759	2515377	2047	384.672
31224643	2515378	1004	64.93
31224762	2515378	1005	24.0943
31224763	2515378	2047	711.89
31224764	2515378	2048	668.54428
31224658	2515379	1004	45.02
31224766	2515379	1005	27.6943
31224767	2515379	2047	597.98
31224768	2515379	2048	560.68798
31224613	2515380	1004	40.03
2235774	325198	1003	18
31224770	2515380	1005	18.6751
31224771	2515380	2047	554.602
31224772	2515380	2048	514.83917
31224628	2515381	1004	48.44
2235805	325198	1008	366
31224774	2515381	1005	24.5007
31224775	2515381	2047	609.456
31224776	2515381	2048	570.651
31224598	2515382	1004	15.28
31224778	2515382	1005	58.9025
31224779	2515382	2047	437.684
31224780	2515382	2048	423.62737
2265404	333374	1085	0.32
2265388	333374	1005	0
2265345	333374	1004	0.45
13338555	333374	2047	69
13338457	333374	2048	74
2236052	325287	1003	0.55
2236075	325287	1008	37
2236397	325430	1008	42
2236428	325430	1003	0.91
24474113	1999626	2048	35.107387
24474112	1999626	2047	42.6655
24474110	1999626	1005	7.5897
24469997	1999626	1004	0.2563
24474120	1999627	2048	33.31965
24474119	1999627	2047	41.0475
24474117	1999627	1005	6.94125
24470030	1999627	1004	0.1875
24474127	1999628	2048	36.3915875
24474126	1999628	2047	44.095
24474124	1999628	1005	8.170625
24469931	1999628	1004	0.195
24474134	1999629	2048	24.8732585
24474133	1999629	2047	31.2172
24474131	1999629	1005	4.079375
2236792	325524	1008	612
2236810	325524	1003	21
24469964	1999629	1004	0.3708
24474146	1999630	1085	1.8841
24474142	1999630	2048	38.485
24474141	1999630	2047	38.485
24474138	1999630	1005	1.293125
24469778	1999630	1004	2.125
24474173	1999631	1085	1.1089
24474169	1999631	2048	14.5562
24474168	1999631	2047	14.5562
24474165	1999631	1005	0.3371125
24469881	1999631	1004	1.221
2265982	333476	1005	0
2265910	333476	1004	0.41
2265998	333476	1085	0.4
13338550	333476	2047	53
13338452	333476	2048	56
24474192	1999632	2048	20.72851125
24474191	1999632	2047	26.602
24474189	1999632	1005	2.406325
24470091	1999632	1004	0.6188
24474200	1999633	2048	21.5796975
24474199	1999633	2047	27.63
24474197	1999633	1005	2.64135
24470123	1999633	1004	0.6044
24474208	1999634	2048	18.95436075
24474207	1999634	2047	21.9574
2227784	323121	1085	26
24474205	1999634	1005	3.837475
24470059	1999634	1004	0.425
2227684	323121	1004	28
2227685	323121	1005	2.89
13338529	323121	2047	310
13338431	323121	2048	314
2237619	325658	1003	18.2
2237575	325658	1008	322
2238528	325871	1003	9.43
2238521	325871	1008	270
2228470	323294	1005	16.2
2228557	323294	1085	53.4
2228469	323294	1004	57.8
13338507	323294	2047	667
13338409	323294	2048	621
2239144	325992	1008	169
2239103	325992	1003	16.7
2240131	326135	1003	27
2240073	326135	1008	393
2240334	326196	1003	2.94
2240316	326196	1008	36
2229339	323444	1004	11
2229391	323444	1085	10.3
2229340	323444	1005	6.86
2229571	323505	1005	4.42
2229570	323505	1004	1.49
13338486	323505	2047	43
13338591	323505	2048	35
2240958	326458	1003	0.81
2240989	326458	1008	37
33293988	2644281	2048	336.59519
33293987	2644281	2047	345.223
33293986	2644281	1005	59.7955
33293892	2644281	1004	2.199
33293992	2644282	2048	371.99469
33293991	2644282	2047	382.998
33293990	2644282	1005	60.358
33293906	2644282	1004	6.274
33293996	2644283	2048	350.9328
33293995	2644283	2047	360.285
33293994	2644283	1005	62.17125
33293920	2644283	1004	1.925
33294000	2644284	2048	345.5703
33293999	2644284	2047	354.015
33293998	2644284	1005	61.836
33293934	2644284	1004	2.419
33293948	2644285	1004	1.273
33294002	2644285	1005	19.8135
33294003	2644285	2047	118.361
33294004	2644285	2048	115.28233
33293963	2644286	1004	1.398
33294006	2644286	1005	19.98325
33294007	2644286	2047	118.79
33294008	2644286	2048	115.82665
33293978	2644287	1004	1.169
33294010	2644287	1005	18.8185
33294011	2644287	2047	115.445
33294012	2644287	2048	112.0972
33293787	2644288	1004	3.096
33294014	2644288	1005	20.32025
33294015	2644288	2047	137.22
33294016	2644288	2048	132.972
2229858	323604	1005	0.91
33293802	2644289	1004	1.256
2229857	323604	1004	10.3
13338536	323604	2047	146
13338438	323604	2048	150
33294018	2644289	1005	21.03
33294019	2644289	2047	126.624
33294020	2644289	2048	123.17082
33293817	2644290	1004	1.299
33294022	2644290	1005	21.44675
33294023	2644290	2047	126.703
33294024	2644290	2048	123.51359
33293832	2644291	1004	1.153
33294026	2644291	1005	12.707625
33294027	2644291	2047	80.145
33294028	2644291	2048	77.798925
33293847	2644292	1004	1.266
33294030	2644292	1005	19.60025
33294031	2644292	2047	116.57
33294032	2644292	2048	113.59675
33293862	2644293	1004	1.296
33294034	2644293	1005	19.1659
33294035	2644293	2047	116.0276
33294036	2644293	2048	112.882483
33293877	2644294	1004	1.266
33294038	2644294	1005	19.32875
33294039	2644294	2047	116.834
33294040	2644294	2048	113.66287
2241684	326698	1003	4.25
2268179	334194	1005	0.08
2268217	334194	1085	0.6
2268126	334194	1004	0.94
2241721	326698	1008	61
2230126	323697	1004	0.16
2230127	323697	1005	0.74
13338554	323697	2047	45
13338456	323697	2048	48
13338552	334194	2047	85
13338454	334194	2048	90
2242226	326905	1008	249
2242227	326905	1003	3.3
2268391	334247	1005	99.6
2230393	323793	1005	6.02
2230392	323793	1004	0.65
13338556	323793	2047	350
13338458	323793	2048	376
2268382	334247	1004	0.32
2242728	327046	1003	1.06
2242732	327046	1008	58
2243089	327198	1003	0.82
2243060	327198	1008	34
8523955	747429	1085	26.6
2230984	324038	1004	0.19
2230975	324038	1005	6.74
8523933	747429	1004	31.1
8523956	747429	1005	6.35
13338565	747429	2047	375
13338460	747429	2048	373
2243584	327357	1003	1.06
2243546	327357	1008	39
8524371	747447	1085	0.07
8524279	747447	1005	6.27
8524297	747447	1004	0.34
13338488	747447	2047	39
13338390	747447	2048	32
8524405	747448	1004	0.22
2243980	327501	1008	47
8524459	747448	1085	0.17
2243939	327501	1003	0.91
8524419	747448	1005	7.63
13338497	747448	2047	35
13338399	747448	2048	31
2231464	324157	1004	28.7
2231565	324157	1085	27.1
2231465	324157	1005	3.37
2244386	327699	1008	31
2244375	327699	1003	0.64
13338528	324157	2047	325
13338430	324157	2048	329
2244897	327923	1008	17
2244936	327923	1003	1.24
2232271	324317	1085	12.6
2232166	324317	1004	14.4
2232167	324317	1005	36.3
13338515	324317	2047	293
13338417	324317	2048	289
33295320	2646168	2048	173.72307
33295319	2646168	2047	167.769
33295318	2646168	1005	-0.48175
33295184	2646168	1004	9.469
33295324	2646169	2048	125.02117
33295323	2646169	2047	119.039
33295322	2646169	1005	-0.59425
33295202	2646169	1004	3.899
33295328	2646170	2048	112.20227
33295327	2646170	2047	106.034
33295326	2646170	1005	-0.368
33295220	2646170	1004	1.934
33295332	2646171	2048	149.306475
33295331	2646171	2047	144.07
33295330	2646171	1005	-0.406
33295238	2646171	1004	7.916
33295336	2646172	2048	259.611425
33295335	2646172	2047	254.11
33295334	2646172	1005	-0.30625
33295256	2646172	1004	20.04
33295340	2646173	2048	146.43655
33295339	2646173	2047	140.635
33295338	2646173	1005	0.852
33295274	2646173	1004	5.703
33295344	2646174	2048	237.096894
33295343	2646174	2047	231.7048
33295342	2646174	1005	-0.5238
33295292	2646174	1004	17.8
33295348	2646175	2048	169.562744
33295347	2646175	2047	163.8998
33295346	2646175	1005	-0.30405
33295310	2646175	1004	9.399
8525379	747693	1005	26.8
8525366	747693	1004	0.55
13338566	747693	2047	117
13338461	747693	2048	103
24474215	2003586	2048	365.610744
24474214	2003586	2047	357.0648
24474213	2003586	1005	74.4462
24474075	2003586	1004	1.52
24474221	2003587	2048	340.5831
24474220	2003587	2047	363.679
24474219	2003587	1005	70.723
24474096	2003587	1004	2.543
24474227	2003588	2048	365.0310665
24474226	2003588	2047	356.5918
24474225	2003588	1005	73.82345
24474033	2003588	1004	1.597
24474233	2003589	2048	365.9569125
24474232	2003589	2047	357.81
24474231	2003589	1005	71.98575
24474054	2003589	1004	1.838
24474239	2003590	2048	47.231814
24474238	2003590	2047	48.3763
24474237	2003590	1005	11.3639625
24473988	2003590	1004	0.2863
24474246	2003591	2048	45.723
24474245	2003591	2047	47.225
24474244	2003591	1005	10.340625
24474016	2003591	1004	0.325
24474253	2003592	2048	64.513952
24474252	2003592	2047	66.1099
24474251	2003592	1005	15.6227875
24473932	2003592	1004	0.2875
24474260	2003593	2048	64.64055
24474259	2003593	2047	66.135
24474258	2003593	1005	15.84375
24473960	2003593	1004	0.265
24474267	2003594	2048	31.298775
24474266	2003594	2047	32.0925
24474265	2003594	1005	7.26375
24473876	2003594	1004	0.3375
24474274	2003595	2048	39.830778
24474273	2003595	2047	41.0921
24474272	2003595	1005	9.1021875
24473904	2003595	1004	0.2669
24474281	2003596	2048	19.92788125
24474280	2003596	2047	23.3075
24474279	2003596	1005	4.320625
24473820	2003596	1004	0.2875
24474288	2003597	2048	44.960839
24474287	2003597	2047	46.5063
24474286	2003597	1005	10.0124
24473848	2003597	1004	0.3563
24474296	2003598	2048	26.024813
24474295	2003598	2047	32.4385
24474293	2003598	1005	4.6583
24473767	2003598	1004	0.3117
24474303	2003599	2048	38.460412
24474302	2003599	2047	46.3951
24474300	2003599	1005	8.50065
24473789	2003599	1004	0.3075
2233034	324653	1004	0.43
2233035	324653	1005	1.99
13338492	324653	2047	14
13338394	324653	2048	12
24474310	2003600	2048	36.7150215
24474309	2003600	2047	44.4382
24474307	2003600	1005	8.141425
24473723	2003600	1004	0.245
24474317	2003601	2048	23.722638
24474316	2003601	2047	30.2035
24474314	2003601	1005	4.01455
24473745	2003601	1004	0.1967
24474324	2003602	2048	30.9734625
24474323	2003602	2047	37.615
24474321	2003602	1005	6.604375
24473679	2003602	1004	0.265
24474331	2003603	2048	32.995442
24474330	2003603	2047	39.8018
24474328	2003603	1005	6.7593
24473701	2003603	1004	0.4494
24474338	2003604	2048	31.236476
24474337	2003604	2047	39.2148
24474335	2003604	1005	5.7637
24473657	2003604	1004	0.24
2247787	328637	1008	408
2247800	328637	1003	23.3
2271378	334849	1004	6.39
2271450	334849	1085	5.93
2271379	334849	1005	0
8526272	747997	1005	2.36
8526268	747997	1004	0
13338551	747997	2047	52
13338453	747997	2048	55
2271587	334897	1004	6.36
2271651	334897	1085	5.56
2271588	334897	1005	0
2271680	334898	1005	0
2271716	334898	1085	2.23
2271679	334898	1004	2.48
2248933	328841	1003	11
2248903	328841	1008	84
2271901	334963	1004	2.41
2271987	334963	1085	2.14
2271902	334963	1005	0
2234311	324860	1085	47.9
2234280	324860	1005	22.3
2234279	324860	1004	51.1
13338509	324860	2047	639
13338411	324860	2048	597
33296841	2647437	2048	50.07437
33296840	2647437	2047	50.0015
33296837	2647437	1005	8.07675
33296677	2647437	1004	0.0875
33296848	2647438	2048	390.73759
33296847	2647438	2047	391.73
33296844	2647438	1005	1.8979
33296703	2647438	1004	32.63
33296855	2647439	2048	307.89587
33296854	2647439	2047	309.768
33296851	2647439	1005	8.1898
33296728	2647439	1004	23.86
33296862	2647440	2048	356.900072
33296861	2647440	2047	356.794
33296858	2647440	1005	2.45312
33296753	2647440	1004	28.13
33296869	2647441	2048	298.113882
33296868	2647441	2047	297.088
33296865	2647441	1005	2.40202
33296778	2647441	1004	22.1
33296876	2647442	2048	297.41686
33296875	2647442	2047	297.608
33296872	2647442	1005	2.9572
33296803	2647442	1004	23.36
33296883	2647443	2048	351.761072
33296882	2647443	2047	351.4
33296879	2647443	1005	2.72432
33296828	2647443	1004	27.24
2272355	335061	1005	0
2272352	335061	1004	11.4
2272368	335061	1085	10.5
8527182	748236	1005	1.02
8527177	748236	1004	28.8
13338532	748236	2047	328
13338434	748236	2048	334
2272589	335109	1085	4.5
2272525	335109	1004	5.32
2272576	335109	1005	0
2251263	329370	1003	23.7
2251279	329370	1008	298
2235088	325036	1005	12.4
2235087	325036	1004	28
2235194	325036	1085	24
13338519	325036	2047	420
13338421	325036	2048	420
2251660	329490	1008	575
2251649	329490	1003	48.1
2273114	335240	1004	3.55
2273221	335240	1085	2.98
2273115	335240	1005	43.1
13338561	335240	2047	254
2251947	329596	1003	15.6
2251952	329596	1008	296
2252277	329716	1003	34.2
2252282	329716	1008	654
2235803	325198	1004	30.6
2235829	325198	1085	27.6
2235804	325198	1005	5.27
13338517	325198	2047	368
13338419	325198	2048	366
2252851	329830	1008	326
2252809	329830	1003	24.5
2236073	325287	1004	0.7
2236074	325287	1005	7.59
13338512	325287	2047	39
13338414	325287	2048	37
2253847	330137	1008	61
2236433	325430	1004	0.27
2253784	330137	1003	10.3
2236396	325430	1005	10.1
13338504	325430	2047	46
13338406	325430	2048	42
2254371	330415	1008	83
2254406	330415	1003	8.06
2236791	325524	1005	17.1
2236790	325524	1004	56.1
2236870	325524	1085	52.1
13338508	325524	2047	657
13338410	325524	2048	612
2254843	330458	1008	833
2254832	330458	1003	0
2255930	330869	1003	27.1
2255933	330869	1008	220
2237668	325658	1085	25.8
27790251	2257044	2047	40.705
27790248	2257044	1005	2.996125
27790191	2257044	1004	1.957
27790257	2257045	2047	19.33
27790254	2257045	1005	0.67075
2237574	325658	1005	2.15
2237573	325658	1004	26.2
27790240	2257045	1004	1.558
27790263	2257046	2047	48.3298
27790260	2257046	1005	5.100325
27790136	2257046	1004	2.749
8530321	748967	1005	0.96
8530329	748967	1085	8.65
8530310	748967	1004	9.96
13338537	748967	2047	143
13338439	748967	2048	147
2238564	325871	1085	3.45
2238519	325871	1004	3.59
2238520	325871	1005	49.2
13338526	325871	2047	267
13338428	325871	2048	270
2259100	331897	1008	156
2239143	325992	1005	0.93
2239142	325992	1004	10.4
2259079	331897	1003	23.9
2239221	325992	1085	8.86
2259565	331960	1003	32.1
2259526	331960	1008	166
27791510	2258586	2048	44.978589
27791509	2258586	2047	47.9906
27791508	2258586	1005	10.26755
27791386	2258586	1004	0.3506
27791514	2258587	2048	38.250983
27791513	2258587	2047	40.7723
27791512	2258587	1005	9.0787
27791407	2258587	1004	0.1375
27791518	2258588	2048	19.692148
27791517	2258588	2047	22.9291
27791516	2258588	1005	4.7781
27791472	2258588	1004	0.1063
27791522	2258589	2048	26.58104025
27791521	2258589	2047	30.7743
27791520	2258589	1005	6.601725
27791494	2258589	1004	0.1211
27791526	2258590	2048	26.98843175
27791525	2258590	2047	31.3256
27791524	2258590	1005	6.653175
27791429	2258590	1004	0.1256
27791530	2258591	2048	27.38430325
27791529	2258591	2047	31.7404
27791528	2258591	1005	6.703125
27791451	2258591	1004	0.1556
2240072	326135	1005	1.44
2240071	326135	1004	31
2261075	332282	1003	1.41
2240163	326135	1085	27.6
2261049	332282	1008	45
2240315	326196	1005	5.3
2240314	326196	1004	1.21
13338487	326196	2047	44
13338389	326196	2048	36
2261743	332397	1008	106
2261732	332397	1003	16.7
27793272	2259792	2048	42.9180362
27793271	2259792	2047	42.835
27793268	2259792	1005	4.811522
2262323	332597	1008	57
27793176	2259792	1004	1.081
27793279	2259793	2048	77.3185458
27793278	2259793	2047	77.9524
27793275	2259793	1005	5.574928
27793213	2259793	1004	4.484
2262288	332597	1003	0.38
27793287	2259794	2048	94.507135
27793286	2259794	2047	93.6776
27793283	2259794	1005	4.75402
27793098	2259794	1004	4.394
27793295	2259795	2048	404.763418
27793294	2259795	2047	403.384
27793291	2259795	1005	4.33188
27793138	2259795	1004	29.5
2241020	326458	1085	0.33
2240945	326458	1004	0.47
2240988	326458	1005	7.92
27793303	2259796	2048	273.472498
27793302	2259796	2047	272.884
27793299	2259796	1005	5.58318
27793253	2259796	1004	19.08
2263143	332791	1008	130
2263121	332791	1003	1.15
2263548	332864	1003	19.3
2263569	332864	1008	346
2241765	326698	1085	3.76
2241720	326698	1005	5.3
2241719	326698	1004	3.38
13338496	326698	2047	69
13338398	326698	2048	61
2264179	333008	1008	430
2264158	333008	1003	5.79
2264831	333281	1003	0.84
2264833	333281	1008	18
2242225	326905	1005	63.9
2242263	326905	1004	0.92
2265346	333374	1008	74
2265357	333374	1003	16.3
2242730	327046	1005	14
2242664	327046	1004	0.44
13338502	327046	2047	64
13338404	327046	2048	58
2265908	333476	1008	56
27793985	2261420	2048	577.62536
27793984	2261420	2047	622.042
27793983	2261420	1005	16.24925
2265951	333476	1003	12.3
27793941	2261420	1004	50.23
27793989	2261421	2048	386.429275
27793988	2261421	2047	389.125
27793987	2261421	1005	69.91725
27793975	2261421	1004	6.309
27793993	2261422	2048	352.682197
27793992	2261422	2047	360.7805
27793991	2261422	1005	79.9422
27793926	2261422	1004	0.9513
2243105	327198	1004	0.18
2243059	327198	1005	8.16
2243545	327357	1005	9.18
2243611	327357	1085	0.13
2243547	327357	1004	0.28
13338503	327357	2047	43
13338405	327357	2048	39
27795962	2262072	2048	589.383006
27795961	2262072	2047	631.65
27795960	2262072	1005	22.70376
27795898	2262072	1004	49.43
27795966	2262073	2048	648.374268
27795965	2262073	2047	697.1376
27795964	2262073	1005	14.179
27795953	2262073	1004	62.4
27795970	2262074	2048	602.510076
27795969	2262074	2047	645.456
27795968	2262074	1005	21.23666
27795789	2262074	1004	53.04
27795974	2262075	2048	514.44343
27795973	2262075	2047	545.084
27795972	2262075	1005	34.3551
27795842	2262075	1004	37.28
2243958	327501	1004	0.15
2243979	327501	1005	11.8
2268170	334194	1003	19
8535575	749420	1005	2.1
8535574	749420	1085	34.6
2268127	334194	1008	90
8535542	749420	1004	36.5
13338567	749420	2047	501
13338462	749420	2048	512
2268383	334247	1008	385
2268373	334247	1003	0
2244425	327699	1085	0.17
2244385	327699	1005	7.63
2244371	327699	1004	0.22
9636110	789890	1005	77.3
9636109	789890	1004	1.48
13338568	789890	2047	366
13338463	789890	2048	375
9636244	789951	1005	73.2
9636243	789951	1004	1.48
13338569	789951	2047	359
13338464	789951	2048	367
9636392	790018	1004	1.7
9636393	790018	1005	74.6
13338570	790018	2047	362
13338465	790018	2048	370
9636544	790085	1005	71.2
2244896	327923	1005	3.24
2244901	327923	1004	0.26
9636543	790085	1004	2.73
13338571	790085	2047	370
13338466	790085	2048	346
9636705	790146	1004	1.65
9636706	790146	1005	72.8
13338572	790146	2047	363
13338467	790146	2048	372
9636844	790214	1004	1.3
9636845	790214	1005	79.8
13338573	790214	2047	359
13338468	790214	2048	369
9637020	790276	1005	80.8
9636993	790276	1004	1.74
13338574	790276	2047	364
13338469	790276	2048	372
2271380	334849	1008	155
2271393	334849	1003	22.8
2271589	334897	1008	176
2271612	334897	1003	27.7
2271681	334898	1008	122
2271652	334898	1003	23.4
27797247	2263887	2048	32.66565
27797246	2263887	2047	36.4
27797245	2263887	1005	7.964375
27797235	2263887	1004	0.22
27797252	2263888	2048	51.412425
27797251	2263888	2047	57.3375
27797250	2263888	1005	12.904375
27797162	2263888	1004	0.1875
27797257	2263889	2048	57.384621
27797256	2263889	2047	63.8563
27797255	2263889	1005	14.571775
27797180	2263889	1004	0.3063
27797262	2263890	2048	77.151876
27797261	2263890	2047	85.919
27797260	2263890	1005	20.196825
27797112	2263890	1004	0.1638
27797267	2263891	2048	71.939325
27797266	2263891	2047	80.1025
27797265	2263891	1005	18.60375
27797133	2263891	1004	0.2325
27797272	2263892	2048	46.4205
27797271	2263892	2047	51.6025
27797270	2263892	1005	12.2615625
27797209	2263892	1004	0.1625
2271903	334963	1008	123
2271931	334963	1003	23.7
2272304	335061	1003	27.3
2272344	335061	1008	219
2272548	335109	1003	22.7
2272539	335109	1008	145
2273153	335240	1003	12.3
2273116	335240	1008	254
9638545	790577	1005	9.93
9638542	790577	1004	0.1
13338575	790577	2047	44
13338470	790577	2048	42
9638712	790646	1004	0.05
9638715	790646	1005	8.61
13338576	790646	2047	38
13338471	790646	2048	36
9638953	790774	1005	20.1
9638935	790774	1004	0.22
28912756	2346384	2048	104.574374
28912755	2346384	2047	102.921
28912753	2346384	1005	4.59964
28912422	2346384	1004	4.225
28912761	2346385	2048	336.73206
28912760	2346385	2047	342.794
28912758	2346385	1005	4.55615
28912394	2346385	1004	33.49
28912766	2346386	2048	335.8933226
28912765	2346386	2047	343.31
28912763	2346386	1005	3.799506
28912366	2346386	1004	35.56
28912771	2346387	2048	192.7437716
28912770	2346387	2047	196.4148
28912768	2346387	1005	5.555506
28912338	2346387	1004	17.99
28912775	2346388	2048	14.455425
28912487	2346388	1004	0.07375
28912773	2346388	1005	3.36875
28912774	2346388	2047	17.10875
28912779	2346389	2048	17.45834025
28912471	2346389	1004	0.07125
28912777	2346389	1005	4.055575
28912778	2346389	2047	20.77105
28912783	2346390	2048	14.68480875
28912782	2346390	2047	17.5315
28912455	2346390	1004	0.1063
28912781	2346390	1005	3.260575
28912787	2346391	2048	18.4931365
28912786	2346391	2047	22.0463
28912438	2346391	1004	0.1563
28912785	2346391	1005	4.06615
28912791	2346392	2048	642.99228
28912790	2346392	2047	688.614
28912789	2346392	1005	18.5921
28912637	2346392	1004	61.27
28912795	2346393	2048	583.597022
28912794	2346393	2047	625.75
28912793	2346393	1005	20.03462
28912599	2346393	1004	51.09
9639407	790991	1005	23
9639429	790991	1004	0.29
28912799	2346394	2048	678.66434
28912798	2346394	2047	729.556
28912797	2346394	1005	10.9096
28912561	2346394	1004	69.74
28912803	2346395	2048	699.59644
28912802	2346395	2047	750.152
28912801	2346395	1005	12.6993
28912523	2346395	1004	73.28
13335660	1104647	1004	0.38
13335663	1104647	1005	28.2
13338480	1104647	2048	130
13338585	1104647	2047	143
28912807	2346396	2048	378.866123
28912806	2346396	2047	381.626
28912805	2346396	1005	68.65755
28912746	2346396	1004	5.89
28912811	2346397	2048	379.2080612
28912810	2346397	2047	381.248
28912809	2346397	1005	69.75082
28912721	2346397	1004	5.8
28912815	2346398	2048	54.046596
28912814	2346398	2047	60.1113
28912813	2346398	1005	14.0914625
28912693	2346398	1004	0.2113
28912820	2346399	2048	63.290775
28912819	2346399	2047	70.5425
28912818	2346399	1005	16.163125
28912665	2346399	1004	0.1925
28912825	2346400	2048	33.5679465
28912824	2346400	2047	39.9998
28912823	2346400	1005	7.41245
28912071	2346400	1004	0.275
28912830	2346401	2048	80.9523625
28912829	2346401	2047	83.42
28912828	2346401	1005	17.77125
28912053	2346401	1004	0.36
28912835	2346402	2048	73.373425
28912834	2346402	2047	75.5575
28912833	2346402	1005	16.27
28912026	2346402	1004	0.2475
28912840	2346403	2048	71.575228
28912839	2346403	2047	73.4738
28912838	2346403	1005	15.9624
28911999	2346403	1004	0.2638
28912845	2346404	2048	77.35727375
28912844	2346404	2047	78.999
28912843	2346404	1005	17.327875
28912150	2346404	1004	0.375
28912850	2346405	2048	14.83215875
28912125	2346405	1004	0.1625
28912848	2346405	1005	3.3165125
28912849	2346405	2047	16.6973
28912110	2346406	1004	0.1775
13335866	1104705	1005	32.9
13335864	1104705	1004	3.33
13338582	1104705	2047	366
13338477	1104705	2048	339
28912852	2346406	1005	2.9525
28912853	2346406	2047	15.9075
28912854	2346406	2048	13.934925
28912858	2346407	2048	27.8695
2247872	328637	1085	29
2247786	328637	1005	2.44
2247826	328637	1004	34
13338518	328637	2047	409
13338420	328637	2048	408
28912095	2346407	1004	0.2275
28912856	2346407	1005	6.38375
28912857	2346407	2047	31.4275
28912862	2346408	2048	29.943836
28912242	2346408	1004	0.2138
28912860	2346408	1005	6.7899
28912861	2346408	2047	34.0538
28912866	2346409	2048	32.66565
28912865	2346409	2047	36.4
28912864	2346409	1005	7.964375
28912224	2346409	1004	0.22
28912871	2346410	2048	51.412425
28912870	2346410	2047	57.3375
28912869	2346410	1005	12.904375
28912198	2346410	1004	0.1875
28912876	2346411	2048	57.384621
28912875	2346411	2047	63.8563
28912874	2346411	1005	14.571775
28912169	2346411	1004	0.3063
28912881	2346412	2048	77.151876
28912880	2346412	2047	85.919
28912879	2346412	1005	20.196825
28912305	2346412	1004	0.1638
28912886	2346413	2048	71.939325
28912885	2346413	2047	80.1025
28912884	2346413	1005	18.60375
28912284	2346413	1004	0.2325
28912891	2346414	2048	46.4205
28912890	2346414	2047	51.6025
28912889	2346414	1005	12.2615625
28912266	2346414	1004	0.1625
13336132	1104766	1005	27.9
13336136	1104766	1004	20.7
13338583	1104766	2047	452
13338478	1104766	2048	421
13336281	1104812	1005	75.5
13336280	1104812	1004	3.85
13338580	1104812	2047	365
13338475	1104812	2048	368
13336410	1104867	1004	1.16
13336426	1104867	1005	80.1
13338581	1104867	2047	358
13338476	1104867	2048	368
13336528	1104913	1004	1.64
13336544	1104913	1005	77.2
13338579	1104913	2047	359
13338474	1104913	2048	367
13336681	1104962	1004	0.13
13336696	1104962	1005	7.68
13338584	1104962	2047	36
13338479	1104962	2048	33
13336919	1105073	1005	20.1
13336901	1105073	1004	0.22
13338577	1105073	2047	85
13338472	1105073	2048	77
2248982	328841	1085	1.87
2248901	328841	1004	2.3
2248902	328841	1005	4.31
13338535	328841	2047	82
13338437	328841	2048	84
13337384	1105314	1005	23
13337406	1105314	1004	0.29
13338578	1105314	2047	98
13338473	1105314	2048	88
13337622	1105430	1005	14.8
13337617	1105430	1004	0.21
13338586	1105430	2047	62
13338481	1105430	2048	56
13337808	1105547	1004	0.1
13337813	1105547	1005	14.7
13338590	1105547	2047	60
13338485	1105547	2048	54
13337999	1105664	1004	0.14
13338004	1105664	1005	14.2
13338589	1105664	2047	59
13338484	1105664	2048	53
13338190	1105781	1004	0.15
13338195	1105781	1005	14.8
13338588	1105781	2047	61
13338483	1105781	2048	55
13338381	1105897	1004	0.16
13338386	1105897	1005	15.7
13338587	1105897	2047	65
13338482	1105897	2048	58
21115640	1750337	1085	1.8841
21115511	1750337	2048	38.485
21115510	1750337	2047	38.485
21115509	1750337	1005	1.293125
21115385	1750337	1004	2.125
2251278	329370	1005	4.44
2251336	329370	1085	17.8
2251277	329370	1004	20.4
13338525	329370	2047	296
13338427	329370	2048	298
2251652	329490	1004	39.8
2251653	329490	1005	1.87
13338539	329490	2047	558
13338441	329490	2048	576
2251945	329596	1005	0.59
2251944	329596	1004	25.1
13338534	329596	2047	291
13338436	329596	2048	297
2252276	329716	1005	1.07
2252275	329716	1004	55.5
13338533	329716	2047	641
13338435	329716	2048	654
2252850	329830	1005	2.07
2252849	329830	1004	24.3
2252932	329830	1085	21.4
2253845	330137	1004	0.37
2253846	330137	1005	3.64
2253907	330137	1085	0.17
13338542	330137	2047	59
13338444	330137	2048	61
2254369	330415	1004	0.15
2254370	330415	1005	12.2
2254471	330415	1085	0.15
13338527	330415	2047	82
13338429	330415	2048	83
8513953	746758	1003	27.7
8513930	746758	1008	176
8514009	746759	1008	155
8514022	746759	1003	22.8
8514080	746760	1003	23.4
8514109	746760	1008	122
8514175	746761	1003	23.7
8514147	746761	1008	123
8514246	746762	1008	145
8514255	746762	1003	22.7
8514337	746763	1008	219
8514297	746763	1003	27.3
8514412	746764	1008	37
8514381	746764	1003	0.81
8514474	746765	1003	24.5
2254891	330458	1085	90.5
2254870	330458	1005	0.84
8514516	746765	1008	326
2254841	330458	1004	99.1
13338506	330458	2047	895
13338408	330458	2048	833
8514663	746766	1008	157
8514626	746766	1003	7.81
8514804	746767	1003	27
8514746	746767	1008	393
8514839	746768	1008	249
8514840	746768	1003	3.3
8514968	746769	1003	1.24
8514929	746769	1008	17
8515015	746770	1003	0.82
8514986	746770	1008	34
8515054	746771	1003	0.91
8515095	746771	1008	47
8515138	746772	1008	43
8515110	746772	1003	3.38
8515260	746773	1003	0.38
8515295	746773	1008	57
8515489	746776	1008	34
8515482	746776	1003	3.43
8515632	746777	1003	1.44
8515641	746777	1008	29
8515674	746778	1003	3.36
8515726	746778	1008	50
8515905	746779	1003	13.3
8515857	746779	1008	328
8515976	746780	1008	322
8516020	746780	1003	18.2
8516174	746781	1008	346
8516153	746781	1003	19.3
8516234	746782	1003	3.27
8516212	746782	1008	60
8516377	746783	1008	169
8516336	746783	1003	16.7
8516467	746784	1008	385
8516457	746784	1003	0
8516553	746785	1003	27.1
8516556	746785	1008	220
2255955	330869	1085	10.4
2255932	330869	1005	0
2255877	330869	1004	11.6
8518379	746952	1008	121
8518363	746952	1003	19.6
8523957	747429	1008	375
8523941	747429	1003	17.5
8524280	747447	1008	31
8524317	747447	1003	2.57
8524420	747448	1008	31
8524409	747448	1003	0.64
8525378	747693	1003	1.11
8525380	747693	1008	117
8526271	747997	1008	55
8526265	747997	1003	10.7
8527179	748236	1003	16.2
8527183	748236	1008	334
8530288	748967	1003	12.4
8530276	748967	1008	148
8535576	749420	1008	500
8535560	749420	1003	40.9
9636111	789890	1008	366
9636105	789890	1003	10.9
9636252	789951	1003	13.1
9636245	789951	1008	358
9636394	790018	1008	362
9636411	790018	1003	12
9636545	790085	1008	370
9636553	790085	1003	15.1
9636689	790146	1003	14.3
9636707	790146	1008	363
9636846	790214	1008	359
9636848	790214	1003	6.94
9637019	790276	1003	6.2
9637021	790276	1008	364
9638546	790577	1008	44
9638540	790577	1003	0.94
9638710	790646	1003	0.83
9638716	790646	1008	38
9638952	790774	1003	0.73
9638955	790774	1008	85
9639423	790991	1003	0.74
9639408	790991	1008	97
13335662	1104647	1003	6.62
13335664	1104647	1008	143
13335867	1104705	1008	366
13335869	1104705	1003	51.1
13336133	1104766	1008	452
13336134	1104766	1003	38.6
13336276	1104812	1003	7.19
13336282	1104812	1008	365.25
13336425	1104867	1003	6.69
13336427	1104867	1008	358
13336545	1104913	1008	358
13336543	1104913	1003	8.75
13336697	1104962	1008	35
13336695	1104962	1003	0.89
13336918	1105073	1003	0.73
13336921	1105073	1008	85
13337385	1105314	1008	97
13337400	1105314	1003	0.74
13337621	1105430	1003	0.19
13337623	1105430	1008	62
13337814	1105547	1008	60
13337812	1105547	1003	0.1
13338003	1105664	1003	0.27
13338005	1105664	1008	59
13338194	1105781	1003	0.13
13338196	1105781	1008	61
13338387	1105897	1008	65
13338385	1105897	1003	0.15
21115508	1750337	1003	3.546875
21115512	1750338	1003	0.5546875
21115516	1750339	1003	0.1875
21115520	1750340	1003	0.1484375
21115524	1750341	1003	0.1328125
21115528	1750342	1003	0.265625
21115532	1750343	1003	0.1015625
21115536	1750344	1003	2.5
21115540	1750345	1003	2.89875
21115544	1750346	1003	2.414375
21115548	1750347	1003	2.890625
21115552	1750352	1003	2.851875
21115556	1750353	1003	2.90625
21115560	1750354	1003	0.695625
24474109	1999626	1003	2.5
24474116	1999627	1003	2.89875
24474123	1999628	1003	2.414375
24474130	1999629	1003	2.890625
24474137	1999630	1003	3.546875
24474164	1999631	1003	0.5546875
24474188	1999632	1003	2.851875
24474196	1999633	1003	2.90625
24474204	1999634	1003	0.695625
24474212	2003586	1003	11.4
24474218	2003587	1003	14.475
24474224	2003588	1003	11.73125
24474230	2003589	1003	13.33125
24474236	2003590	1003	0.0859375
24474243	2003591	1003	0.734375
24474250	2003592	1003	0.2578125
24474257	2003593	1003	0.09375
24474264	2003594	1003	0
24474271	2003595	1003	0.5703125
24474278	2003596	1003	0.859375
24474285	2003597	1003	0.8125
24474292	2003598	1003	2.75
24474299	2003599	1003	2.40625
24474306	2003600	1003	2.416875
24474313	2003601	1003	3.09375
24474320	2003602	1003	2.203125
24474327	2003603	1003	2.18
24474334	2003604	1003	3.5
27790247	2257044	1003	2.776875
27790253	2257045	1003	0.65625
27790259	2257046	1003	0.796875
27791507	2258586	1003	0.94125
27791511	2258587	1003	0.805
27791515	2258588	1003	0.715
27791519	2258589	1003	0.819375
27791523	2258590	1003	0.895625
27791527	2258591	1003	0.881875
27793267	2259792	1003	3.464978
27793274	2259793	1003	3.824172
27793282	2259794	1003	8.77888
27793290	2259795	1003	30.13912
27793298	2259796	1003	19.70782
27793982	2261420	1003	26.24375
27793986	2261421	1003	13.16875
27793990	2261422	1003	8.1125
27795959	2262072	1003	23.99124
27795963	2262073	1003	19.7054
27795967	2262074	1003	20.78734
27795971	2262075	1003	18.0359
27797244	2263887	1003	0.640625
27797249	2263888	1003	1.008125
27797254	2263889	1003	0.703125
27797259	2263890	1003	0.914375
27797264	2263891	1003	0.89875
27797269	2263892	1003	0.2734375
28912752	2346384	1003	11.62436
28912757	2346385	1003	5.78985
28912762	2346386	1003	2.017994
28912767	2346387	1003	3.070694
28912772	2346388	1003	0.7425
28912776	2346389	1003	0.976875
28912780	2346390	1003	0.883125
28912784	2346391	1003	1.09375
28912788	2346392	1003	15.7039
28912792	2346393	1003	21.45038
28912796	2346394	1003	14.5644
28912800	2346395	1003	9.9587
28912804	2346396	1003	13.49645
28912808	2346397	1003	12.51118
28912812	2346398	1003	0.4609375
28912817	2346399	1003	1.039375
28912822	2346400	1003	1.96875
28912827	2346401	1003	2.27375
28912832	2346402	1003	2.0625
28912837	2346403	1003	1.8125
28912842	2346404	1003	1.578125
28912847	2346405	1003	0.4921875
28912851	2346406	1003	0.625
28912855	2346407	1003	0.96125
28912859	2346408	1003	1.2425
28912863	2346409	1003	0.640625
28912868	2346410	1003	1.008125
28912873	2346411	1003	0.703125
28912878	2346412	1003	0.914375
28912883	2346413	1003	0.89875
28912888	2346414	1003	0.2734375
31222702	2512371	1003	13.2093
31222706	2512372	1003	11.92235
31222710	2512373	1003	8.27277
31222714	2512374	1003	8.88492
31222718	2512375	1003	8.3952
31222722	2512376	1003	8.72168
31222726	2512377	1003	0.918225
31222730	2512378	1003	11.06534
31222734	2512379	1003	10.02177
31222738	2512380	1003	7.25305
31222742	2512381	1003	7.03885
31223362	2514743	1003	18.15625
31223366	2514744	1003	17.53125
31223370	2514745	1003	17.80625
31223374	2514746	1003	17.9125
31223378	2514747	1003	17.34375
31224741	2515373	1003	15.03684
31224745	2515374	1003	17.4423
31224749	2515375	1003	13.4938
31224753	2515376	1003	23.205
31224757	2515377	1003	5.28675
31224761	2515378	1003	7.7857
31224765	2515379	1003	20.5057
31224769	2515380	1003	29.9079
31224773	2515381	1003	18.8733
31224777	2515382	1003	16.1385
33293985	2644281	1003	21.5625
33293989	2644282	1003	21.275
33293993	2644283	1003	23.56875
33293997	2644284	1003	21.225
33294001	2644285	1003	6.9125
33294005	2644286	1003	6.56875
33294009	2644287	1003	7.4125
33294013	2644288	1003	7.01875
33294017	2644289	1003	7.8
33294021	2644290	1003	7.30625
33294025	2644291	1003	4.734375
33294029	2644292	1003	6.69375
33294033	2644293	1003	6.925
33294037	2644294	1003	7.03125
33295317	2646168	1003	21.11875
33295321	2646169	1003	21.58125
33295325	2646170	1003	22.525
33295329	2646171	1003	18.6125
33295333	2646172	1003	18.74375
33295337	2646173	1003	21.475
33295341	2646174	1003	18.4
33295345	2646175	1003	20.13125
33296836	2647437	1003	4.22675
33296843	2647438	1003	22.6171
33296850	2647439	1003	15.5672
33296857	2647440	1003	23.45288
33296864	2647441	1003	22.14498
33296871	2647442	1003	18.8848
33296878	2647443	1003	23.83568
\.


--
-- Data for Name: nutrient; Type: TABLE DATA; Schema: foods; Owner: admin
--

COPY foods.nutrient (id, name, unit_name) FROM stdin;
1003	Protein	G
1008	Energy	KCAL
2047	Energy (Atwater General Factors)	KCAL
2048	Energy (Atwater Specific Factors)	KCAL
1004	Total lipid (fat)	G
1005	Carbohydrate, by difference	G
1085	Total fat (NLEA)	G
\.


--
-- Name: food pk_food; Type: CONSTRAINT; Schema: foods; Owner: admin
--

ALTER TABLE ONLY foods.food
    ADD CONSTRAINT pk_food PRIMARY KEY (fdc_id);


--
-- Name: food_nutrient pk_food_nutrient; Type: CONSTRAINT; Schema: foods; Owner: admin
--

ALTER TABLE ONLY foods.food_nutrient
    ADD CONSTRAINT pk_food_nutrient PRIMARY KEY (id);


--
-- Name: nutrient pk_nutrient; Type: CONSTRAINT; Schema: foods; Owner: admin
--

ALTER TABLE ONLY foods.nutrient
    ADD CONSTRAINT pk_nutrient PRIMARY KEY (id);


--
-- Name: food_nutrient fk_food_nutrient_food; Type: FK CONSTRAINT; Schema: foods; Owner: admin
--

ALTER TABLE ONLY foods.food_nutrient
    ADD CONSTRAINT fk_food_nutrient_food FOREIGN KEY (fdc_id) REFERENCES foods.food(fdc_id);


--
-- Name: food_nutrient fk_food_nutrient_nutrient; Type: FK CONSTRAINT; Schema: foods; Owner: admin
--

ALTER TABLE ONLY foods.food_nutrient
    ADD CONSTRAINT fk_food_nutrient_nutrient FOREIGN KEY (nutrient_id) REFERENCES foods.nutrient(id);


--
-- PostgreSQL database dump complete
--

