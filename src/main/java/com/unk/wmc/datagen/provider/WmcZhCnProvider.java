package com.unk.wmc.datagen.provider;

import com.unk.wmc.Wmc;
import com.unk.wmc.block.WmcBlocks;
import com.unk.wmc.item.WmcItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class WmcZhCnProvider extends LanguageProvider {
    public WmcZhCnProvider(PackOutput output) {
        super(output, Wmc.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(WmcItems.SMITHING_TEMPLATE_DUST.get(), "锻造模板粉尘");
        add(WmcItems.SMITHING_TEMPLATE_CORE.get(), "锻造模板核心");
        add(WmcItems.EMPTY_SMITING_TEMPLATE.get(), "空白锻造模板");
        add(WmcItems.BLUEPRINT.get(), "蓝图");
        add(WmcItems.RANDOM_BLUEPRINT.get(), "随机蓝图");

        add(WmcItems.RAW_TERMINUS_INGOT.get(), "粗终焉锭");
        add(WmcBlocks.TERMINUS_BLOCK.get(), "终焉块");
        add(WmcItems.TERMINUS_INGOT.get(), "终焉锭");
        add(WmcItems.TERMINUS_NUGGET.get(), "终焉粒");
        add(WmcItems.TERMINUS_UPGRADE_SMITHING_TEMPLATE.get(), "锻造模板");

        add(WmcItems.COPPER_COGWHEEL.get(), "铜齿轮");
        add(WmcItems.IRON_COGWHEEL.get(), "铁齿轮");
        add(WmcItems.GOLD_COGWHEEL.get(), "金齿轮");
        add(WmcItems.DIAMOND_COGWHEEL.get(), "钻石齿轮");
        add(WmcItems.NETHERITE_COGWHEEL.get(), "下界合金齿轮");

        add(WmcBlocks.ACTIVATE_ALTAR.get(), "激活祭坛");
        add(WmcBlocks.ACTIVATE_PEDESTAL.get(), "激活祭坛基座");

        add(WmcBlocks.ASSEMBLY_TABLE.get(), "装配台");


        add("item.wmc.smithing_template.terminus_upgrade.applies_to", "下界合金装备");
        add("item.wmc.smithing_template.terminus_upgrade.ingredients", "终焉锭");
        add("upgrade.wmc.terminus_upgrade", "终焉升级");
        add("item.wmc.smithing_template.terminus_upgrade.base_slot_description", "放入下界合金盔甲、武器或工具");
        add("item.wmc.smithing_template.terminus_upgrade.additions_slot_description", "放入终焉合金锭");

        add("item.wmc.blueprint.description.progress", "进度: ");
        add("item.wmc.blueprint.description.result", "结果: ");
        add("item.wmc.blueprint.description.next_step", "下一步: ");

        add("item.wmc.general.unknown_data_components", "未知或损坏的数据组件");
        add("item.wmc.general.unknown_item", "未知的物品");
        add("item.wmc.general.activated", "已激活");
        add("item.wmc.general.unactivated", "未激活");
    }
}
