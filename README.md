# TEBEX CORE SYNC

## What is it?

Tebex Core Sync is a simple plugin that will set off a 'placebo purchase' on your store, resulting in adding the entry automatically. This will later allow players to upgrade their rank in your store properly, making sure tebex knows what rank is already present. It is the perfect solution to allow players to obtain ranks ingame as well as later have the option to upgrade it in your store without you having to add the rank manually.

## Version Support

The native version is 1.16. Other Minecraft versions will most likely work, however, we do not guarantee it. If you run a different version than 1.16, feel free to test our plugin and contact us on discord if you run into issues so we can update the plugin and add compatibility.

## Requirements

Obviously you will need a working tebex store. A third party plugin that can execute commands is strongly recommended to automate the process fully, see below.

## Recommendation

We personally recommend the use of an additional plugin to fully automate the process of synchronizing player ranks to your tebex. One option would be for instance a Deluxemenu or Vouchers, which is commonly used for rank books to allow trading between players as well as automatic claiming. Any plugin that executes commands on player actions will work.

We also recommend to disable tebex donation messages if the value equals 0$. If you do not disable this, a donation message will be sent from tebex to your game if you have one set each time a player claims a rank ingame. This can get very spammy, but is useful for testing.

## Commands

    /applytotebex <rankid> <username>

## Permissions

TebexCoreSync.use (this permission is given to OP by default)

## How to Setup

1.) Place the jar file into your server's plugins folder. Do not install it on bungee, if you have one. It is only needed on servers where players may claim ranks in any way.

2.) Restart your server.

3.) Open the config file of Tebex Core Sync. You will find two simple settings here: 'apikey' and 'url'. Simply add your tebex api key but do not touch the url setting.

Not sure where your tebex key is at? Log in to your tebex. On the left, click on 'Integrations' and go to 'Servers' in that dropdown. Here, all your servers are listed if you set up tebex correctly. Click 'edit' on the server for which you are adding the key. You will find that the 'Secret Key' is partially hidden. Click 'Show Secret Key' and simply copy it from there and paste it into the config file. Do NOT add quotation marks! Avoid mixing up servers!

### So how do I automate syncing?

Since the plugin uses a command you will need to add said command to whatever method you are using for players to obtain a rank. This varies depending on what third party plugin you are using.

Example: If you use Vouchers as third party plugin, you will need to add the command listed above into the commands that gets executed when players claim it.

### But where or what is a rank id?!

For tebex to add the correct rank to a player, you need to use the appropriate id. If you add the wrong id, tebex will execute the command for said id, resulting in potentially giving the player the wrong rank!

To find the id's of each rank you offer in your store, go to your store. Navigate to the rank you are offering and view the page of said rank, basically where your players would buy them. Make sure you view the single rank, not the list of all ranks! Depending on your setup, you may have to even click on 'add to basket' in your store for the id to show up in the URL.

The URL in your browser will reveal the rank id. Here is an example:
https://store.slimesociety.net/checkout/packages/add/4101719/single

The rank id is the number in the url of the exact rank, in our case 4101719. So the command we would add to our voucher would be 'applytotebex 4101719 %player%'. The placeholder to fetch the player depends on your third party plugin. Vouchers take %player%, other plugins may use a different placeholder. You will have to look up which placeholder your plugin uses.

## Experiencing delays?

The command the plugin sends basically tells Tebex that there was a purchase made for 0$ for said rank. If you are experiencing delays between the command execution and the rank actually being applied to the player, then this is because Tebex needs to process the 'placebo payment'. It is the same as if a player was to actually buy a rank, just without payment. To make this process instant, we recommend you add the command to set a player's rank to the third party plugin itself as well. Here is an example using vouchers:

    Commands:
      - 'lp user %Player% parent settrack rank Novice'
      - 'applytotebex 4101719 %Player%'

This will cause our permissions plugin, in this case LuckPerms, to set the rank for the player ingame, as well as send the 'placebo purchase' to the store, which will send the same command a little later, depending on how quick your tebex is. It is virtually the same thing - you don't need to add both methods, just understand that delays happen due to tebex processing the placebo payment.

## Support

If you are having trouble with the setup of our plugin or you found a bug, we will gladly help you out on our Discord.
Please keep in mind that we may be in a completely different time zone than you. Breaking our discord rules may result in a kick or ban from there, making it impossible for you to receive support. Be nice to us and we will be nice to you, too! :)
Here is the invite: https://discord.gg/T9T73NyYdF


## Licence MIT

Copyright 2021 SlimeSociety.net

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## Aditional note

# WE ARE NOT IN ANY WAY AFFILIATED WITH TEBEX LTD. OR Mojang Studios