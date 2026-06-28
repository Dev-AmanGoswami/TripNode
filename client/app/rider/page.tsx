import type { Metadata } from "next";
import Link from "next/link";
import { Icon } from "@/components/Icon";
import { BookingForm } from "@/components/BookingForm";

export const metadata: Metadata = { title: "Premium Booking" };

const navItems = [
  { label: "Book a Ride", icon: "rocket_launch", primary: true },
  { label: "Your Rides", icon: "route", primary: false },
  { label: "Profile", icon: "person", primary: false },
];

export default function RiderPage() {
  return (
    <>
      {/* Abstract background layers (decorative, fixed) */}
      <div className="velocity-mesh fixed inset-0 -z-10 overflow-hidden">
        <div className="velocity-lines" />
        <div className="glow-orb left-[-10%] top-[-10%]" />
        <div
          className="glow-orb bottom-[-20%] right-[-10%]"
          style={{ background: "radial-gradient(circle, rgba(71,71,71,0.15) 0%, transparent 70%)" }}
        />
      </div>

      {/* Header */}
      <header className="fixed left-0 top-0 z-50 flex h-24 w-full items-center justify-between border-b border-outline-variant/30 bg-transparent px-margin-desktop backdrop-blur-md">
        <Link href="/" className="text-headline-xl font-bold tracking-tighter text-primary">
          Velocity
        </Link>
        <nav className="hidden items-center gap-md md:flex">
          {navItems.map((item) => (
            <button
              key={item.label}
              className={`flex items-center gap-sm rounded-full px-lg py-sm text-label-md transition-all ${
                item.primary
                  ? "bg-primary text-on-primary hover:scale-105 hover:shadow-[0_0_20px_rgba(198,198,198,0.3)]"
                  : "border border-outline-variant/50 bg-surface-container-highest/50 text-on-surface hover:bg-surface-container-highest"
              }`}
            >
              <Icon name={item.icon} size={20} />
              {item.label}
            </button>
          ))}
        </nav>
        <button aria-label="Menu" className="p-sm text-on-surface md:hidden">
          <Icon name="menu" />
        </button>
      </header>

      {/* Main */}
      <main className="flex h-screen w-full items-center justify-center px-md pt-24">
        <div className="z-20 w-full max-w-[540px]">
          <BookingForm />
        </div>
      </main>
    </>
  );
}
