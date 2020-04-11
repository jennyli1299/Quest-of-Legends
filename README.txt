Name: Jenny Li
BU ID: U63136209

Hello there! In order to compile and execute my code, please use the following two commands:
1. javac GameController.java
2. java GameController


Usability of Classes:
1. GameController -> Game Center simply executes the QUEST.
2. Playable Interface -> Interface for all things Playable. All classes which implement or extend a class that implements this interface must have play() methods
3. Quest -> Runs the game and controls how everything interacts to play, including map movement, battles, business in the market, etc.
4. Battle -> Controls each battle and how it plays.
5. Market -> A class in which an instance represents the Market of the QUEST.
6. Item -> Abstract class Item which provides a foundation for the items in the market.
7. Armor -> Concrete class which extends Item. Each instance of the Armor class represents a specific piece of armor.
8. Weapon -> Concrete class which extends Item. Each instance of the Weapon class represents a specific and unique weapon.
9. Potion -> Concrete class which extends Item. Each instance of the Potion class represents a specific potion.
10. Spell -> Class which extends Item, representing a spell.
11. FireSpell -> Concrete class which extends Spell. Each instance represents a specific FIRE type Spell. Apart from the damage it causes, a FireSpell also reduces the defense level of the enemy. 
12. IceSpell -> Concrete class which extends Spell. Each instance represents a specific ICE type Spell. Apart from the damage it causes, an IceSpell also reduces the damage range of the enemy. 
13. LightningSpell -> Concrete class which extends Spell. Each instance represents a specific LIGHTNING type Spell. Apart from the damage, a LightningSpell causes it also reduces the dodge chance of the enemy. 
14. Tile -> Class which represents each individual tile that build up a board in a board game. Determines whether each location of the QUEST Map is unaccessible, a market, or a common tile.
15. Board -> Class of a Board used in board games commposed of indidual tiles. In this case, it represents the Map of the QUEST.
16. InGameCharacter -> Abstract Class representing the different types of in-game-characters in QUEST
17. Hero -> Class blueprint of a Hero. Each instance represents an individual Heroes in the team of Heroes. Each Hero has their own stats, inventories, etc.
18. Warrior -> Concrete class which extends Hero. Each instance of the Warrior class represents a unique WARRIOR type Hero. Warriors are favored on the strength and the agility.  
19. Paladin -> Concrete class which extends Hero. Each instance of the Paladin class represents a unique PALADIN type Hero. Paladins are favored on the strength and the dexterity.  
20. Sorcerer -> Concrete class which extends Hero. Each instance of the Sorcerer class represents a unique SORCERER type Hero. Sorcerers are favored on the dexterity and the agility. 
21. Monster -> Class is a blueprint of a Monster. Each instance represents an individual Monsters that exist in QUEST. Each Monster has their own stats.
22. Dragon -> Concrete class which extends Monster. Each instance of the Dragon class represents a unique DRAGON type Monster. Dragons have higher damage.
23. Exoskeleton -> Concrete class which extends Monster. Each instance of the Exoskeleton class represents a unique EXOSKELETON type Monster. Exoskeletons have increased defense.
24. Spirit -> Concrete class which extends Monster. Each instance of the Spirit class represents a unique SPIRIT type Monster. Spirits have higher dodge chance.
25. SetUp -> Loads all the data for the QUEST.
26. Game -> Abstract Game class from which Quest extends.


Adjustments to the Logistics of QUEST:
I posted on piazza and asked to make about most of these adjustments prior to make sure they were alright.
1. Monsters can also level up.
2. The fight will consist of one monster that is the highest hero's level +/- 1 and the rest will be random monsters not above the highest hero's level.
3. Spells deteriorate Monster's skills in battle by 20% of the spell's damage instead of the 10% of the Monster's own skills.
4. When heroes win a battle, they all get compensated a flat 150 coins but ones that are still standing get compensated an extra 10*(highest monster's level) for their troubles.

Thank You! I hope you enjoy! :)