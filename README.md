# Disable Portals
A simple mod that allows for the disabling of nether portals
and end portals. It adds the `disableportals` command for all
operators.

## Command Usage
`disableportals reloadConfig` - reloads the config file, 
located in `config/DisablePortals.json`

`disableportals (netherAllowed|endAllowed|endGatewaysAllowed)
(true|false)` - sets the selected dimension's portals to work
or not to work.Ex: `disableportals endAllowed false` will
turn off end portals.


## Config Usage
A config file (`config/DisablePortals.json`) will auto 
generated.

By default, no portals are disabled. To disable nether
portals through the config, set `disableNetherPortals` to 
true. If you make changes to the config while the game is
running, you will have to run `disableportals reloadConfig`
for the changes to take effect.

<br>

<a href="https://modrinth.com/mod/fabric-api">
  <img src="https://i.imgur.com/HabVZJR.png" width="200">
</a>
